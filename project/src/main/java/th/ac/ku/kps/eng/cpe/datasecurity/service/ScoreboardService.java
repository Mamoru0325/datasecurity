package th.ac.ku.kps.eng.cpe.datasecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Scoreboard;
import th.ac.ku.kps.eng.cpe.datasecurity.repository.ScoreboardRepository;

@Service
public class ScoreboardService {
	@Autowired
	private ScoreboardRepository scoreboardRepository;

	public Scoreboard save(Scoreboard scoreboard) {
		return scoreboardRepository.save(scoreboard);
	}

	public List<Scoreboard> findAll() {
		return (List<Scoreboard>) scoreboardRepository.findAll();
	}

	public Scoreboard findById(int scoreboardId) {
		return scoreboardRepository.findById(scoreboardId).orElse(null);
	}

	public void delete(Scoreboard scoreboard) {
		scoreboardRepository.delete(scoreboard);
	}

	public List<Object> findByLevelOrderByscoreDESC(String level, int page, int value) {
		Pageable pageable = PageRequest.of(page, value);
		return scoreboardRepository.findByLevelOrderByscoreDESC(level, pageable);
	}

}
