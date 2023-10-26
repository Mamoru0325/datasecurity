package th.ac.ku.kps.eng.cpe.datasecurity.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
	
	@Query("from Feedback fb where month(fb.date) = :month and year(fb.date) = :year")
	public List<Feedback> findAllByMonthAndYear (@Param("month")int month, @Param("year")int year, Pageable pageable);
	
	@Query("from Feedback fb order by fb.date desc")
	public List<Feedback> findAllPagination (Pageable pageable);

}
