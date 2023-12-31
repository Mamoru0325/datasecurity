package th.ac.ku.kps.eng.cpe.datasecurity.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import th.ac.ku.kps.eng.cpe.datasecurity.api.response.Response;
import th.ac.ku.kps.eng.cpe.datasecurity.model.Feedback;
import th.ac.ku.kps.eng.cpe.datasecurity.model.User;
import th.ac.ku.kps.eng.cpe.datasecurity.service.FeedbackService;
import th.ac.ku.kps.eng.cpe.datasecurity.service.UserService;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("http://localhost:8081/")
public class FeedbackRestController {
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private UserService userService;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<ObjectNode>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Response<ObjectNode> res = new Response<>();
		res.setHttpStatus(HttpStatus.BAD_REQUEST);

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode responObject = mapper.createObjectNode();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldname = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			responObject.put(fieldname, errorMessage);
		});
		res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		res.setBody(responObject);
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}

	@PostMapping("/")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('User') or hasRole('Admin')")
	public ResponseEntity<Response<Feedback>> create(@RequestBody Feedback feedback) {
		Response<Feedback> res = new Response<>();
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userService.findByUserName(username);
			feedback.setDate(new Date());
			feedback.setUser(user);
			feedback = feedbackService.save(feedback);
			res.setBody(feedback);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Response<Feedback>>(res, res.getHttpStatus());
	}

	@GetMapping("/")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<List<?>>> findAll() {
		Response<List<?>> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		try {
			List<Feedback> feedbacks = feedbackService.findAllOrderByDesc();
			for (Feedback feedback : feedbacks) {
				User user = feedback.getUser();
				ObjectNode responseObject = mapper.valueToTree(feedback);
				responseObject.put("username", user.getUsername());
				responseList.add(responseObject);
			}
			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
	}

	@GetMapping("/month/{month}/year/{year}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<List<?>>> findByMonthAndYear(@PathVariable("month") int month, @PathVariable("year") int year) {
		Response<List<?>> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		try {
			List<Feedback> feedbacks = feedbackService.findAllByMonthAndYear(month, year);
			for (Feedback feedback : feedbacks) {
				User user = feedback.getUser();
				ObjectNode responseObject = mapper.valueToTree(feedback);
				responseObject.put("username", user.getUsername());
				responseList.add(responseObject);
			}
			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
	}
	
	@GetMapping("/year")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<List<Integer>>> findYearInFeedback (){
		Response<List<Integer>> res = new Response<>();
		try {
			List<Integer> year = feedbackService.findYearInFeedback();
			res.setBody(year);
			res.setHttpStatus(HttpStatus.OK);
			
		} catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<List<Integer>>>(res,res.getHttpStatus());
	}
	
	@GetMapping("/month")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<List<Integer>>> findMonthInFeedback (){
		Response<List<Integer>> res = new Response<>();
		try {
			List<Integer> month = feedbackService.findMonthInFeedback();
			res.setBody(month);
			res.setHttpStatus(HttpStatus.OK);
			
		} catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<List<Integer>>>(res,res.getHttpStatus());
	}

}
