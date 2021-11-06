package inreco.vlgu.mobile.repository;



import inreco.vlgu.mobile.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("select q from Question q where q.number>=:first and  q.number<:last")
    List<Question> comeGetSomeQuestions(@Param("first") Integer first, @Param("last") Integer last);
}
