package inreco.vlgu.mobile.controllers;


import inreco.vlgu.mobile.dto.auth.response.MessageResponse;
import inreco.vlgu.mobile.dto.give_answer.InputRequest;
import inreco.vlgu.mobile.dto.simple.AnswerResponse;
import inreco.vlgu.mobile.dto.auth.request.SignupRequest;
import inreco.vlgu.mobile.dto.simple.QuestionResponse;
import inreco.vlgu.mobile.model.*;
import inreco.vlgu.mobile.repository.*;

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
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    AnswerTOquestionRepository answerTOquestionRepository;

    @Autowired
    AttemptRepository attemptRepository;



    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/client")
   // @PreAuthorize("hasRole('client') or hasRole('master') or hasRole('admin')")
    public String userAccess() {
        return "Client Content.";
    }


    @GetMapping("/admin")
   // @PreAuthorize("hasRole('admin')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/questions")
    public ResponseEntity<?>  questions() {
        return ResponseEntity.ok(new QuestionResponse(questionRepository.findAll()));
    }

    @GetMapping("/answers")
    public ResponseEntity<?>  answers() {
        return ResponseEntity.ok(new AnswerResponse(answerRepository.findAll()));
    }

    @PostMapping("/giveAnswer")
    public ResponseEntity<?> giveAnswer(@Valid @RequestBody InputRequest inputRequest, Principal principal) {
        //TO DO: вынести в сервис и доработать. сделать у юзера поле с id текущей попытки ну или брать исходя из статутса финиша
        Question q = questionRepository.getById(inputRequest.getIdQ());
        Answer a = answerRepository.getById(inputRequest.getIdA());
        User user = userRepository.findByUsername(principal.getName()).get();
        Attempt attempt = attemptRepository.getCurrentAttempt(user.getId());
        AnswerTOquestion aTq = new AnswerTOquestion();
        aTq.setUser(user);
        aTq.setAnswer(a);
        aTq.setQuestion(q);
        aTq.setAttempt(attempt);
        answerTOquestionRepository.save(aTq);
        return ResponseEntity.ok(new MessageResponse("Answer registered successfully!"));
    }
}
