package inreco.vlgu.mobile.controllers;


import inreco.vlgu.mobile.dto.account.AccountResponse;
import inreco.vlgu.mobile.dto.account.ChangeRequest;
import inreco.vlgu.mobile.dto.auth.response.MessageResponse;
import inreco.vlgu.mobile.model.User;
import inreco.vlgu.mobile.repository.UserRepository;
import inreco.vlgu.mobile.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService accountService;

    @GetMapping("/info")
    @ApiOperation("Возвращает информацию об аккаунте")
    public ResponseEntity<AccountResponse> account(Principal principal) {
        User user =userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(accountService.getAccount(user));
    }


    @PostMapping("/change")
    @ApiOperation("Изменяет nickname")
    public ResponseEntity<MessageResponse> change(@Valid @RequestBody ChangeRequest changeRequest, Principal principal) {
        User user =userRepository.findByUsername(principal.getName()).get();
        if(accountService.changeName(user, changeRequest.getName()))
            return ResponseEntity.ok(new MessageResponse("Name has been changed!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }
}
