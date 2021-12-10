package inreco.vlgu.mobile.service;


import inreco.vlgu.mobile.model.*;
import inreco.vlgu.mobile.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;



@Service
public class TestService  {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    AnswerTOquestionRepository answerTOquestionRepository;

    @Autowired
    AttemptRepository attemptRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    FactorRepository factorRepository;

    @Transactional
    public boolean giveAnswer(User user, Long idQ, Long idA)  {
        Question q = questionRepository.getById(idQ);
        Answer a = answerRepository.getById(idA);
        Attempt attempt = attemptRepository.getCurrentAttempt(user.getId());
        AnswerTOquestion aTq = new AnswerTOquestion();
        aTq.setAnswer(a);
        aTq.setQuestion(q);
        aTq.setAttempt(attempt);
        try{
            answerTOquestionRepository.save(aTq);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean startTest(User user)  {
        Attempt attempt = attemptRepository.getCurrentAttempt(user.getId());
        if(attempt!=null) {
            attemptRepository.delete(attempt);
        }
        attempt = new Attempt();
        attempt.setFinished(false);
        attempt.setUser(user);
        try {
            attemptRepository.save(attempt);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public Long finishTest(User user)  {
        Attempt attempt = attemptRepository.getCurrentAttempt(user.getId());
        if(attempt!=null && attempt.getAnserTOquestionList().size()==questionRepository.count()) {
            attempt.setFinished(true);
            attempt.setDate(new Date(Calendar.getInstance().getTime().getTime()));
            attemptRepository.save(attempt);
            return attempt.getId();
        }
        else
            return null;
    }

    @Transactional
    public List<Result> results(Long id)  {
       Attempt attempt = attemptRepository.getById(id);
       List<Result> results = new ArrayList<>();

       if(attempt.isFinished()){
           int first=0,second=0,third=0,fourth=0,fifth=0,sixth=0, seventh=0;
           double firstMax=0,secondMax=0,thirdMax=0,fourthMax=0,fifthMax=0,sixthMax=0, seventhMax =0;
           double firstMin=0,secondMin=0,thirdMin=0,fourthMin=0,fifthMin=0,sixthMin=0, seventhMin =0;
           boolean sexM = attempt.getUser().isSexM();
               for (AnswerTOquestion aTq:
                    attempt.getAnserTOquestionList()) {
                    switch (Math.toIntExact(aTq.getQuestion().getFactor().getId())){
                        case 1:
                            if(aTq.getQuestion().isPlus())
                                first=first+aTq.getAnswer().getPoints();
                            else
                                first=first+Math.abs(aTq.getAnswer().getPoints()-8);
                            break;
                        case 2:
                            if(aTq.getQuestion().isPlus())
                                second=second+aTq.getAnswer().getPoints();
                            else
                                second=second+Math.abs(aTq.getAnswer().getPoints()-8);
                            break;
                        case 3:
                            if(aTq.getQuestion().isPlus())
                                third=third+aTq.getAnswer().getPoints();
                            else
                                third=third+Math.abs(aTq.getAnswer().getPoints()-8);
                            break;
                        case 4:
                            if(aTq.getQuestion().isPlus())
                                fourth=fourth+aTq.getAnswer().getPoints();
                            else
                                fourth=fourth+Math.abs(aTq.getAnswer().getPoints()-8);
                            break;
                        case 5:
                            if(aTq.getQuestion().isPlus())
                                fifth=fifth+aTq.getAnswer().getPoints();
                            else
                                fifth=fifth+Math.abs(aTq.getAnswer().getPoints()-8);
                            break;
                        case 6:
                            if(aTq.getQuestion().isPlus())
                                sixth=sixth+aTq.getAnswer().getPoints();
                            else
                                sixth=sixth+Math.abs(aTq.getAnswer().getPoints()-8);
                            break;

                    }
               }
               seventh = fifth+second+third+fourth+fifth+sixth;
               for (Factor f:
                    factorRepository.findAll()) {
                   switch (Math.toIntExact(f.getId())){
                       case 1:
                           if(sexM){
                               firstMax = f.getMcriticMale()+f.getQcriticMale();
                               firstMin = f.getMcriticMale()-f.getQcriticMale();
                           }
                           else {
                               firstMax = f.getMcriticFemale()+f.getQcriticFemale();
                               firstMin = f.getMcriticFemale()-f.getQcriticFemale();
                           }
                           Result r1 = new Result();
                           r1.setFactor(f);
                           r1.setPoints(first);
                           System.out.println("first : " + first + " firstMax : "+firstMax+ " firstMin : "+ firstMin);
                           if(first>firstMax) {
                               r1.setDescription("Вы достаточно планомерны, предпочитаете последовательно " +
                                       "реализовывать поставленные цели, имеете развитые навыки " +
                                       "тактического планирования");
                           }
                           else {
                               if(first>firstMin){
                                   r1.setDescription("Вы в умеренной степени склонны разрабатывать четкие планы и " +
                                           "планомерно следовать им при достижении поставленных целей.");
                               }
                               else {
                                   r1.setDescription("Вам может сложно даваться планирование Вашей деятельности " +
                                           "и планомерное следование разработанному плану.");
                               }
                           }
                           results.add(r1);
                           resultRepository.save(r1);
                           break;
                       case 2:
                           if(sexM){
                               secondMax = f.getMcriticMale()+f.getQcriticMale();
                               secondMin = f.getMcriticMale()-f.getQcriticMale();
                           }
                           else {
                               secondMax = f.getMcriticFemale()+f.getQcriticFemale();
                               secondMin = f.getMcriticFemale()-f.getQcriticFemale();
                           }
                           Result r2 = new Result();
                           r2.setFactor(f);
                           r2.setPoints(second);
                           System.out.println("second : " + second + " secondMax : "+secondMax+ " secondMin : "+ secondMin);
                           if(second>secondMax) {
                               r2.setDescription("Вы целеустремлённы и целенаправленны, знаете, чего хотите " +
                                       "и к чему стремитесь, идете по направлению к своим целям.");
                           }
                           else {
                               if(second>secondMin){
                                   r2.setDescription("Вы достаточно хорошо видите и понимаете свои цели, способны " +
                                           "достигать их, хотя в Вашей жизни могут быть периоды, когда " +
                                           "не вся Ваша деятельность направлена на достижение каких-либо " +
                                           "ясных для Вас целей.");
                               }
                               else {
                                   r2.setDescription("Вы не всегда четко видите свои цели или не склонны ставить " +
                                           "перед собой конкретные цели, Вам может быть не свойственно " +
                                           "к чему-либо целенаправленно стремиться и прилагать усилия " +
                                           "для достижения поставленных целей.");
                               }
                           }
                           results.add(r2);
                           resultRepository.save(r2);
                           break;
                       case 3:
                           if(sexM){
                               thirdMax = f.getMcriticMale()+f.getQcriticMale();
                               thirdMin = f.getMcriticMale()-f.getQcriticMale();
                           }
                           else {
                               thirdMax = f.getMcriticFemale()+f.getQcriticFemale();
                               thirdMin = f.getMcriticFemale()-f.getQcriticFemale();
                           }
                           Result r3 = new Result();
                           r3.setFactor(f);
                           r3.setPoints(third);
                           System.out.println("third : " + third + " thirdMax : "+thirdMax+ " thirdMin : "+ thirdMin);
                           if(third>thirdMax) {
                               r3.setDescription("Вас можно охарактеризовать как волевого и организованного " +
                                       "человека, способного усилием воли структурировать свою " +
                                       "поведенческую активность и завершить начатое дело.");
                           }
                           else {
                               if(third>thirdMin){
                                   r3.setDescription("Вы достаточно организованны и структурированны, способны " +
                                           "на волевые усилия, хотя и можете оставлять начатое дело, " +
                                           "переключаясь на более значимые для Вас виды деятельности.");
                               }
                               else {
                                   r3.setDescription("Вам может быть сложно прикладывать волевые усилия для " +
                                           "доведения начатого дела до его логического завершения, " +
                                           "Вы склонны отвлекаться на посторонние дела.");
                               }
                           }
                           results.add(r3);
                           resultRepository.save(r3);
                           break;
                       case 4:
                           if(sexM){
                               fourthMax = f.getMcriticMale()+f.getQcriticMale();
                               fourthMin = f.getMcriticMale()-f.getQcriticMale();
                           }
                           else {
                               fourthMax = f.getMcriticFemale()+f.getQcriticFemale();
                               fourthMin = f.getMcriticFemale()-f.getQcriticFemale();
                           }
                           Result r4 = new Result();
                           r4.setFactor(f);
                           r4.setPoints(fourth);
                           System.out.println("fourth : " + fourth + " fourthMax : "+fourthMax+ " fourthMin : "+ fourthMin);
                           if(fourth>fourthMax) {
                               r4.setDescription("Вы исполнительный и обязательный человек, стремитесь всеми " +
                                       "возможными способами завершить начатое дело. Возможно, " +
                                       "Вы можете быть недостаточно гибкими в планировании своей " +
                                       "деятельности и в построении отношений.");
                           }
                           else {
                               if(fourth>fourthMin){
                                   r4.setDescription("Вы достаточно гибкий человек в планировании своей " +
                                           "деятельности и в построении отношений, тем не менее " +
                                           "Вы стремитесь выполнять данные Вами обязательства.");
                               }
                               else {
                                   r4.setDescription("Вы гибкий человек, легко переключаетесь на новые виды " +
                                           "деятельности и отношения. В отдельных ситуациях Вы можете " +
                                           "восприниматься недостаточно обязательным " +
                                           "и последовательным");
                               }
                           }
                           results.add(r4);
                           resultRepository.save(r4);
                           break;
                       case 5:
                           if(sexM){
                               fifthMax = f.getMcriticMale()+f.getQcriticMale();
                               fifthMin = f.getMcriticMale()-f.getQcriticMale();
                           }
                           else {
                               fifthMax = f.getMcriticFemale()+f.getQcriticFemale();
                               fifthMin = f.getMcriticFemale()-f.getQcriticFemale();
                           }
                           Result r5 = new Result();
                           r5.setFactor(f);
                           r5.setPoints(fifth);
                           System.out.println("fifth : " + fifth + " fifthMax : "+fifthMax+ " fifthMin : "+ fifthMin);
                           if(fifth>fifthMax) {
                               r5.setDescription("Вы обладаете высоким уровнем самоорганизации, при " +
                                       "планировании склонны пользоваться вспомогательными " +
                                       "средствами (ежедневником, планнингом, бюджетированием " +
                                       "времени)");
                           }
                           else {
                               if(fifth>fifthMin){
                                   r5.setDescription("При планировании своего рабочего и личного времени " +
                                           "Вы можете полагаться как на вспомогательные средства " +
                                           "(ежедневники, записные книжки, планнинги), так и на свою " +
                                           "природную организованность.");
                               }
                               else {
                                   r5.setDescription("Вы не склонны при организации своей деятельности прибегать " +
                                           "к помощи внешних средств, помогающих в управлении " +
                                           "временем, что может негативно сказываться на Вашем уровне " +
                                           "самоорганизации.");
                               }
                           }
                           results.add(r5);
                           resultRepository.save(r5);
                           break;
                       case 6:
                           if(sexM){
                               sixthMax = f.getMcriticMale()+f.getQcriticMale();
                               sixthMin = f.getMcriticMale()-f.getQcriticMale();
                           }
                           else {
                               sixthMax = f.getMcriticFemale()+f.getQcriticFemale();
                               sixthMin = f.getMcriticFemale()-f.getQcriticFemale();
                           }
                           Result r6 = new Result();
                           r6.setFactor(f);
                           r6.setPoints(sixth);
                           System.out.println("sixth : " + sixth + " sixthMax : "+sixthMax+ " sixthMin : "+ sixthMin);
                           if(sixth>sixthMax) {
                               r6.setDescription("Вы склонны фиксироваться на происходящем с Вами " +
                                       "в настоящий момент времени, для Вас переживания и " +
                                       "происходящее «здесь-и-сейчас» имеет особую ценность " +
                                       "и значимость.");
                           }
                           else {
                               if(sixth>sixthMin){
                                   r6.setDescription("Вы способны видеть и ценить свое психологическое прошлое " +
                                           "и будущее, наряду с происходящем с Вами в настоящий момент " +
                                           "времени.");
                               }
                               else {
                                   r6.setDescription("Вы склонны находить более ценным Ваше психологическое " +
                                           "прошлое или будущее, нежели происходящее с Вами " +
                                           "«здесь-и-сейчас».");
                               }
                           }
                           results.add(r6);
                           resultRepository.save(r6);
                           break;
                       case 7:
                           if(sexM){
                               seventhMax = f.getMcriticMale()+f.getQcriticMale();
                               seventhMin = f.getMcriticMale()-f.getQcriticMale();
                           }
                           else {
                               seventhMax = f.getMcriticFemale()+f.getQcriticFemale();
                               seventhMin = f.getMcriticFemale()-f.getQcriticFemale();
                           }
                           Result r7 = new Result();
                           r7.setFactor(f);
                           r7.setPoints(seventh);
                           System.out.println("seventh : " + seventh + " seventhMax : "+seventhMax+ " seventhMin : "+ seventhMin);
                           if(seventh>seventhMax) {
                               r7.setDescription("Вам свойственно видеть и ставить цели, планировать свою деятельность, в том числе с помощью внешних средств, и, проявляя волевые " +
                                       "качества и настойчивость, идти к ее достижению. Возможно, в отдельных видах деятельности Вы можете быть чрезмерно структурированны, организованны " +
                                       "и недостаточно гибки. Тем не менее Вы достаточно эффективно можете структурировать свою деятельность.");
                           }
                           else {
                               if(seventh>seventhMin){
                                   r7.setDescription("Вы способны сочетать структурированный подход к организации " +
                                           "времени своей жизни со спонтанностью и гибкостью, умеете " +
                                           "ценить все составляющие Вашего психологического времени и " +
                                           "извлекать для себя ценный опыт из многоплановости своей жизни");
                               }
                               else {
                                   r7.setDescription("Вы предпочитаете жить спонтанно, не привязывать свою " +
                                           "деятельность к жесткой структуре и целям. Ваше будущее для Вас " +
                                           "самого достаточно туманно, Вам не свойственно четко планировать " +
                                           "свою ежедневную активность и прилагать волевые усилия для " +
                                           "завершения начатых дел, однако это позволяет Вам достаточно " +
                                           "быстро и гибко переключаться на новые виды активности, " +
                                           "не «застревая» на структурировании своей деятельности.");
                               }
                           }
                           results.add(r7);
                           resultRepository.save(r7);
                           break;
                   }
               }
               attempt.setResults(results);
               attemptRepository.save(attempt);
               return  results;
       }
       return null;
    }

    public List<Question> getAllQuestions()  {
        return questionRepository.findAll();
    }

    public List<Question> getSomeQuestions(Integer first, Integer last)  {
        if(first<1)
            first=1;
        Long count = questionRepository.count();
        if(last>count)
            last =Math.toIntExact(count)+1;
        return questionRepository.comeGetSomeQuestions(first,last);
    }
    public List<Answer> getAnswers()  {
        return answerRepository.findAll();
    }
    public List<Attempt> getUserAttempts(User user)  {
        return attemptRepository.getAllUserAttempts(user.getId());
    }

}
