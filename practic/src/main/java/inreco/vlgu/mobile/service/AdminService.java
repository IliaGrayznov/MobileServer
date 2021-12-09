package inreco.vlgu.mobile.service;



import inreco.vlgu.mobile.model.*;
import inreco.vlgu.mobile.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    FactorRepository factorRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    AttemptRepository attemptRepository;


    @Transactional
    public boolean addQuestion(String text, Integer nubmer, Boolean plus, Long factor_id)  {
        Question q = new Question();
        q.setFactor(factorRepository.getById(factor_id));
        q.setNumber(nubmer);
        q.setPlus(plus);
        q.setQuestionText(text);
        try {
           questionRepository.save(q);
        }
        catch (Exception e){
            return  false;
        }
        return  true;
    }

    @Transactional
    public boolean changeQuestionText(String text, Long id)  {
        Question q = questionRepository.getById(id);
        q.setQuestionText(text);
        try {
            questionRepository.save(q);
        }
        catch (Exception e){
            return  false;
        }
        return  true;
    }

    @Transactional
    public boolean deleteQuestion(Long id)  {
        Question q = questionRepository.getById(id);
        try {
            questionRepository.delete(q);
        }
        catch (Exception e){
            return  false;
        }
        return  true;
    }

    public List<Result> getAllResults()  {
        return resultRepository.findAll();
    }

    public List<Factor> getFactors()  {
        return factorRepository.findAll();
    }

    public List<Result> getMenResults()  {
        return attemptRepository.getMenResults();
    }

    public List<Result> getWomenResults()  {
        return attemptRepository.getWomenResults();
    }

   /* public List<Result> getMenResults()  {
        List<Result> results = new ArrayList<>();
        for (Attempt a:
                attemptRepository.getMenResults()) {
            results.addAll(a.getResults());
        }
        return results;
    }

    public List<Result> getWomenResults()  {
        List<Result> results = new ArrayList<>();
        for (Attempt a:
                attemptRepository.getWomenResults()) {
            results.addAll(a.getResults());
        }
        return results;
    }*/
}