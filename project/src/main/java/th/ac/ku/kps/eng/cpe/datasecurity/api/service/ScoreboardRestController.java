package th.ac.ku.kps.eng.cpe.datasecurity.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import th.ac.ku.kps.eng.cpe.datasecurity.api.response.Response;
import th.ac.ku.kps.eng.cpe.datasecurity.service.ScoreboardService;

@RestController
@RequestMapping("/api/scoreboard")
@CrossOrigin("http://localhost:8081/")
public class ScoreboardRestController {
	@Autowired
	private ScoreboardService scoreboardService;

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

	@GetMapping("/scores/{level}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('User') or hasRole('Admin')")
	public ResponseEntity<Response<List<?>>> findByLevelOrderByscoreDESC (@PathVariable("level")String level) {
		Response<List<?>> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		try {
			List<Object> scoreboards = scoreboardService.findByLevelOrderByscoreDESC(level, 0, 20);
			for(Object scoreboard :scoreboards) {
				ObjectNode responObject = mapper.createObjectNode();
				Object[] o = (Object[]) scoreboard;
				responObject.put("scoreId", o[0].toString());
				responObject.put("userName", o[1].toString());
				responObject.put("score", o[2].toString());
				responseList.add(responObject);
			}
			
			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);
			
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());

	}

}
