package inreco.vlgu.mobile.controllers;



import inreco.vlgu.mobile.dto.admin.ChangeTextRequest;
import inreco.vlgu.mobile.dto.admin.FuctorResponse;
import inreco.vlgu.mobile.dto.admin.QuestionCreaterRequest;
import inreco.vlgu.mobile.dto.admin.QuestionDeleteRequest;
import inreco.vlgu.mobile.dto.auth.response.MessageResponse;

import inreco.vlgu.mobile.dto.simple.ResultResponse;
import inreco.vlgu.mobile.repository.UserRepository;

import inreco.vlgu.mobile.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminService adminService;

    @PostMapping("/create")
    @ApiOperation("Добавляет вопрос в тетст")
    public ResponseEntity<MessageResponse> account(@Valid @RequestBody QuestionCreaterRequest questionCreaterRequest) {
        if(adminService.addQuestion(questionCreaterRequest.getText(), questionCreaterRequest.getNumber(), questionCreaterRequest.getPlus(), questionCreaterRequest.getFactor_id()))
            return ResponseEntity.ok(new MessageResponse("Question add successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }


    @PostMapping("/change")
    @ApiOperation("Изменяет текст вопроса")
    public ResponseEntity<MessageResponse> changeQuestion(@Valid @RequestBody ChangeTextRequest changeTextRequest) {
        if(adminService.changeQuestionText(changeTextRequest.getText(), changeTextRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Question text has been changed!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @PostMapping("/delete")
    @ApiOperation("Удаляет вопрос")
    public ResponseEntity<MessageResponse> changeQuestion(@Valid @RequestBody QuestionDeleteRequest questionDeleteRequest) {
        if(adminService.deleteQuestion(questionDeleteRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Question has been deleted successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @GetMapping("/statistic/global")
    @ApiOperation("Возвращает все результаты тестов")
    public ResponseEntity<ResultResponse> statistic() {
        return ResponseEntity.ok(new ResultResponse(adminService.getAllResults()));
    }

    @GetMapping("/factors")
    @ApiOperation("Возвращает все факторы")
    public ResponseEntity<FuctorResponse> factors() {
        return ResponseEntity.ok(new FuctorResponse(adminService.getFactors()));
    }

    @GetMapping("/statistic/globalM")
    @ApiOperation("Возвращает все результаты тестов у мужчин")
    public ResponseEntity<ResultResponse> Mstatistic() {
        return ResponseEntity.ok(new ResultResponse(adminService.getMenResults()));
    }

    @GetMapping("/statistic/globalW")
    @ApiOperation("Возвращает все результаты тестов у женщин")
    public ResponseEntity<ResultResponse> Wstatistic() {
        return ResponseEntity.ok(new ResultResponse(adminService.getWomenResults()));
    }
}
