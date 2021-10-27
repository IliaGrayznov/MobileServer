package inreco.vlgu.mobile.repository;



import inreco.vlgu.mobile.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question,Long> {
}
