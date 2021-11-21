package inreco.vlgu.mobile.repository;



import inreco.vlgu.mobile.model.Attempt;
import inreco.vlgu.mobile.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AttemptRepository extends JpaRepository<Attempt,Long> {
    @Query("select a from Attempt a where a.finished=false and  a.user.id=:id")
    Attempt getCurrentAttempt(@Param("id") long id);

    @Query("select a from Attempt a where a.user.id=:id")
    List<Attempt> getAllUserAttempts(@Param("id") long id);

   /* @Query("select a from Attempt a  where a.user.sex_m=true")
    List<Attempt> getMenResults();

    @Query("select a from Attempt a  where a.user.sex_m=false")
    List<Attempt> getWomenResults();*/

    @Query("select a.results from Attempt a  where a.user.sex_m=false")
    List<Result> getWomenResults();

    @Query("select a.results from Attempt a  where a.user.sex_m=true")
    List<Result> getMenResults();

}
