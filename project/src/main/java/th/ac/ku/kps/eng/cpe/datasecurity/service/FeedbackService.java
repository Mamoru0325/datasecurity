package th.ac.ku.kps.eng.cpe.datasecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Feedback;
import th.ac.ku.kps.eng.cpe.datasecurity.repository.FeedbackRepository;

@Service
public class FeedbackService {
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public Feedback save (Feedback feedback) {
		return feedbackRepository.save(feedback);
	}
	
	public List<Feedback> findAll () {
		return (List<Feedback>) feedbackRepository.findAll();
	}
	
	public Feedback findById (int id) {
		return feedbackRepository.findById(id).orElse(null);
	}
	
	public void deleteById (int id) {
		feedbackRepository.deleteById(id);
	}
	
	public List<Feedback> findAllByMonthAndYear (int month, int year) {
		return feedbackRepository.findAllByMonthAndYear(month, year);
	}
	
	public List<Integer> findYearInFeedback () {
		return feedbackRepository.findYearInFeedback();
	}
	
	public List<Integer> findMonthInFeedback () {
		return feedbackRepository.findMonthInFeedback();
	}
	
	public List<Feedback> findAllOrderByDesc () {
		return feedbackRepository.findAllOrderByDesc();	
	}

}
