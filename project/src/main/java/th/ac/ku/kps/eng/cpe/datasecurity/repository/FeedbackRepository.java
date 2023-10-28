package th.ac.ku.kps.eng.cpe.datasecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
	
	@Query("from Feedback fb where month(fb.date) = :month and year(fb.date) = :year")
	public List<Feedback> findAllByMonthAndYear (@Param("month")int month, @Param("year")int year);
	
	@Query("from Feedback fb order by fb.date desc")
	public List<Feedback> findAllOrderByDesc();
	
	@Query("select year(fb.date) from Feedback fb group by year(fb.date)")
	public List<Integer> findYearInFeedback();
	
	@Query("select month(fb.date) from Feedback fb group by month(fb.date)")
	public List<Integer> findMonthInFeedback();

}
