package inreco.vlgu.mobile.repository;




import inreco.vlgu.mobile.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface ResultRepository extends JpaRepository<Result,Long> {

}
