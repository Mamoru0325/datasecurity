package th.ac.ku.kps.eng.cpe.datasecurity.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Scoreboard;

@Repository
public interface ScoreboardRepository extends CrudRepository<Scoreboard, Integer> {
	
	@Query("select s.scoreId, u.username, max(s.score) from Scoreboard s inner join s.user u where u in "
			+ "(select s.user from Scoreboard s where s.level = :level order by s.score desc) and s.level = :level "
			+ "group by u.userId")
	public List<Object> findByLevelOrderByscoreDESC(@Param("level")String level, Pageable pageable);
}
