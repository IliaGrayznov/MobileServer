package inreco.vlgu.mobile.controllers;


import inreco.vlgu.mobile.dto.auth.response.MessageResponse;
import inreco.vlgu.mobile.dto.give_answer.InputRequest;
import inreco.vlgu.mobile.dto.simple.*;
import inreco.vlgu.mobile.dto.auth.request.SignupRequest;
import inreco.vlgu.mobile.model.*;
import inreco.vlgu.mobile.repository.*;

import inreco.vlgu.mobile.service.TestService;
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
    public ResponseEntity<?>  questions() {
        return ResponseEntity.ok(new QuestionResponse(testService.getAllQuestions()));
    }

    @GetMapping("/someQuestions")
    public ResponseEntity<?>  someQuestions(@Valid @RequestBody SomeQuestionRequest someQuestionRequest) {
        return ResponseEntity.ok(new QuestionResponse(testService.getSomeQuestions(someQuestionRequest.getFirst(),someQuestionRequest.getLast())));
    }

    @GetMapping("/answers")
    public ResponseEntity<?>  answers() {
        return ResponseEntity.ok(new AnswerResponse(testService.getAnswers()));
    }

    @GetMapping("/attempts")
    public ResponseEntity<?>  attempts(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new AttemptResponse(testService.getUserAttempts(user)));
    }

   @PostMapping("/startAttempt")
    public ResponseEntity<?>  startAttempt(Principal principal) {
       User user = userRepository.findByUsername(principal.getName()).get();
       if(testService.startTest(user))
           return ResponseEntity.ok(new MessageResponse("Test started!"));
       else
           return ResponseEntity
                   .badRequest()
                   .body(new MessageResponse("Error: Something went wrong("));
    }

    @PostMapping("/giveAnswer")
    public ResponseEntity<?> giveAnswer(@Valid @RequestBody InputRequest inputRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(testService.giveAnswer(user,inputRequest.getIdQ(),inputRequest.getIdA()))
            return ResponseEntity.ok(new MessageResponse("Answer registered successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @PostMapping("/finishAttempt")
    public ResponseEntity<?>  finishAttempt(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(testService.finishTest(user))
            return ResponseEntity.ok(new MessageResponse("Test finished!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Not all questions have answers("));
    }

    @PostMapping("/getResults")
    public ResponseEntity<?>  getResults(@Valid @RequestBody AttemptRequestID attemptRequestID) {
        return ResponseEntity.ok(new ResultResponse(testService.results(attemptRequestID.getId())));
    }
}
