package th.ac.ku.kps.eng.cpe.datasecurity.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import th.ac.ku.kps.eng.cpe.datasecurity.api.response.Response;
import th.ac.ku.kps.eng.cpe.datasecurity.model.Type;
import th.ac.ku.kps.eng.cpe.datasecurity.service.TypeService;

@RestController
@RequestMapping("/api/type")
@CrossOrigin("http://localhost:8081/")
public class TypeRestController {
	@Autowired
	private TypeService typeService;
	
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
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<Type>> create (@RequestBody Type type) {
		Response<Type> res = new Response<>();
		try {
			type = typeService.save(type);
			res.setBody(type);
			res.setHttpStatus(HttpStatus.OK);
			
		}catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<Type>> (res,res.getHttpStatus());
	}
	
	@PutMapping("/{id}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<Type>> create (@RequestBody Type type, @PathVariable("id")int id) {
		Response<Type> res = new Response<>();
		try {
			Type type2 = typeService.findById(id);
			type2.setTypeName(type.getTypeName());
			type2 = typeService.save(type2);
			res.setBody(type2);
			res.setHttpStatus(HttpStatus.OK);
			
		}catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<Type>> (res,res.getHttpStatus());
	}
	
	@DeleteMapping("/{id}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<String>> create (@PathVariable("id")int id) {
		Response<String> res = new Response<>();
		try {
			typeService.deleteById(id);
			res.setBody("Delete typeId " + id + "Complete");
			res.setHttpStatus(HttpStatus.OK);
			
		}catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<String>> (res,res.getHttpStatus());
	}
	
	@GetMapping("/")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<List<Type>>> findAll () {
		Response<List<Type>> res = new Response<>();
		try {
			List<Type> types = typeService.findAll();
			res.setBody(types);
			res.setHttpStatus(HttpStatus.OK);
			
		} catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<List<Type>>>(res,res.getHttpStatus());
	}
	
	@GetMapping("/{id}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<Type>> findById (@PathVariable("id")int id) {
		Response<Type> res = new Response<>();
		try {
			Type type = typeService.findById(id);
			res.setBody(type);
			res.setHttpStatus(HttpStatus.OK);
			
		} catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<Type>>(res,res.getHttpStatus());
	}

}
