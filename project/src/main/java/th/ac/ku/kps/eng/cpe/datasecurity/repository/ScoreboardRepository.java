package th.ac.ku.kps.eng.cpe.datasecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Scoreboard;

@Repository
public interface ScoreboardRepository extends CrudRepository<Scoreboard, Integer> {

}
