package th.ac.ku.kps.eng.cpe.datasecurity.api.service;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import th.ac.ku.kps.eng.cpe.datasecurity.api.response.Response;
import th.ac.ku.kps.eng.cpe.datasecurity.model.User;
import th.ac.ku.kps.eng.cpe.datasecurity.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:8081/")
public class UserRestController {
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
	
	@PutMapping("/{id}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<User>> findById (@PathVariable("id")int id, @RequestBody User user){
		Response<User> res = new Response<>();
		try {
			User user2 = userService.findById(id);
			user2.setStatus(user.getStatus());
			user2 = userService.save(user2);
			res.setBody(user2);
			res.setHttpStatus(HttpStatus.OK);
			
		}catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<User>> (res,res.getHttpStatus());
	}
	
	@GetMapping("/page/{page}/value/{value}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<List<User>>> findAllByRole (@PathVariable("page")int page, @PathVariable("value")int value) {
		Response<List<User>> res = new Response<>();
		try {
			List<User> users = userService.findAllByRole(page, value, "ROLE_Admin");
			res.setBody(users);
			res.setHttpStatus(HttpStatus.OK);
			
		}catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<List<User>>>(res,res.getHttpStatus());
	}

}
