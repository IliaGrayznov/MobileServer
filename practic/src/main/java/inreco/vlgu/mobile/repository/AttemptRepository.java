package inreco.vlgu.mobile.repository;



import inreco.vlgu.mobile.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AttemptRepository extends JpaRepository<Attempt,Long> {
    @Query("select a from Attempt a where a.finished=false and  a.user.id=:id")
    Attempt getCurrentAttempt(@Param("id") long id);
}
