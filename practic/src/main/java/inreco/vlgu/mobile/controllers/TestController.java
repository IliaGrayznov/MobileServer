package inreco.vlgu.mobile.controllers;


import inreco.vlgu.mobile.dto.auth.response.MessageResponse;
import inreco.vlgu.mobile.dto.give_answer.InputRequest;
import inreco.vlgu.mobile.dto.simple.*;
import inreco.vlgu.mobile.dto.auth.request.SignupRequest;
import inreco.vlgu.mobile.model.*;
import inreco.vlgu.mobile.repository.*;

import inreco.vlgu.mobile.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestService testService;



    @GetMapping("/questions")
    @ApiOperation("Возвращает список всех вопросов")
    public ResponseEntity<QuestionResponse>  questions() {
        return ResponseEntity.ok(new QuestionResponse(testService.getAllQuestions()));
    }

    @GetMapping("/someQuestions")
    @ApiOperation("Возвращает список вопросов с first (включая)  по last (не включая)")
    public ResponseEntity<QuestionResponse>  someQuestions(@Valid @RequestBody SomeQuestionRequest someQuestionRequest) {
        return ResponseEntity.ok(new QuestionResponse(testService.getSomeQuestions(someQuestionRequest.getFirst(),someQuestionRequest.getLast())));
    }

    @GetMapping("/answers")
    @ApiOperation("Возвращает список всех ответов")
    public ResponseEntity<AnswerResponse>  answers() {
        return ResponseEntity.ok(new AnswerResponse(testService.getAnswers()));
    }

    @GetMapping("/attempts")
    @ApiOperation("Возвращает список всех попыток пользователя. Результаты прилагаются, где это возможно")
    public ResponseEntity<AttemptResponse>  attempts(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new AttemptResponse(testService.getUserAttempts(user)));
    }

   @PostMapping("/startAttempt")
   @ApiOperation("Создает у пользователя новую попытку. Если предыдущая не завершена то удаляет ее")
    public ResponseEntity<MessageResponse>  startAttempt(Principal principal) {
       User user = userRepository.findByUsername(principal.getName()).get();
       if(testService.startTest(user))
           return ResponseEntity.ok(new MessageResponse("Test started!"));
       else
           return ResponseEntity
                   .badRequest()
                   .body(new MessageResponse("Error: Something went wrong("));
    }

    @PostMapping("/giveAnswer")
    public ResponseEntity<MessageResponse> giveAnswer(@Valid @RequestBody InputRequest inputRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(testService.giveAnswer(user,inputRequest.getIdQ(),inputRequest.getIdA()))
            return ResponseEntity.ok(new MessageResponse("Answer registered successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @PostMapping("/finishAttempt")
    @ApiOperation("Завершает попытку, если ответы есть на все вопросы, возвращая id это попытки")
    public ResponseEntity<?>  finishAttempt(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        Long result = testService.finishTest(user);
        if(result!=null)
            return ResponseEntity.ok(new AttemptRequestID(result));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Not all questions have answers( or maybe you have no attempt to finish"));
    }

    @PostMapping("/getResults")
    @ApiOperation("Высчитывает результаты попытки")
    public ResponseEntity<ResultResponse>  getResults(@Valid @RequestBody AttemptRequestID attemptRequestID) {
        return ResponseEntity.ok(new ResultResponse(testService.results(attemptRequestID.getId())));
    }
}
