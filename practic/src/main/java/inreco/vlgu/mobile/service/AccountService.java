package inreco.vlgu.mobile.service;


import inreco.vlgu.mobile.dto.account.AccountResponse;
import inreco.vlgu.mobile.model.*;
import inreco.vlgu.mobile.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Period;

import java.util.Calendar;



@Service
public class AccountService {
    @Autowired
    UserRepository userRepository;

    public AccountResponse getAccount(User user){
        AccountResponse acR = new AccountResponse();
        acR.setUsername(user.getUsername());
        acR.setSexM(user.isSexM());
        Date d = new Date(Calendar.getInstance().getTime().getTime());
        Integer age = Period.between(user.getAge().toLocalDate(), d.toLocalDate()).getYears();
        acR.setAge(age);
        return acR;
    }

    @Transactional
    public boolean changeName(User user, String name)  {
        user.setUsername(name);
        try{
            userRepository.save(user);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}