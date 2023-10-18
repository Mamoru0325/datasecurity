package th.ac.ku.kps.eng.cpe.datasecurity.api.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import th.ac.ku.kps.eng.cpe.datasecurity.api.response.Response;
import th.ac.ku.kps.eng.cpe.datasecurity.model.User;
import th.ac.ku.kps.eng.cpe.datasecurity.model.dto.LoginDTO;
import th.ac.ku.kps.eng.cpe.datasecurity.model.dto.SignupDTO;
import th.ac.ku.kps.eng.cpe.datasecurity.security.exception.AccountAlreadyExistException;
import th.ac.ku.kps.eng.cpe.datasecurity.security.jwt.JwtUtils;
import th.ac.ku.kps.eng.cpe.datasecurity.security.jwt.response.JwtResponse;
import th.ac.ku.kps.eng.cpe.datasecurity.security.service.UserDetailsImpl;
import th.ac.ku.kps.eng.cpe.datasecurity.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:8081/")
public class AuthRestController {
	@Autowired
	private UserService userService;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder encoder;
	
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
	
	@PostMapping("/signup")
	public ResponseEntity<Response<SignupDTO>> registerUser(@Valid @RequestBody SignupDTO user) {
		Response<SignupDTO> res = new Response<>();
		try {
			String roleName = "ROLE_User";
			User _user = new User();
			_user.signup(user);
			_user.setPassword(encoder.encode(_user.getPassword()));
			userService.registerNewAccount(_user, roleName);
			res.setMessage("Register Success");
			res.setBody(user);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<SignupDTO>>(res, res.getHttpStatus());
		} catch (AccountAlreadyExistException uaeEx) {
			res.setMessage("An account for that username/email already exists.");
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<SignupDTO>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<SignupDTO>>(res, res.getHttpStatus());
		}
	}
	
	@PostMapping("/signup/staff")
	public ResponseEntity<Response<SignupDTO>> registerStaff(@Valid @RequestBody SignupDTO user) {
		Response<SignupDTO> res = new Response<>();
		try {
			String roleName = "ROLE_Admin";
			User _user = new User();
			_user.signup(user);
			_user.setPassword(encoder.encode(_user.getPassword()));
			userService.registerNewAccount(_user, roleName);
			res.setMessage("Register Success");
			res.setBody(user);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<SignupDTO>>(res, res.getHttpStatus());
		} catch (AccountAlreadyExistException uaeEx) {
			res.setMessage("An account for that username/email already exists.");
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<SignupDTO>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setMessage(ex.getLocalizedMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<SignupDTO>>(res, res.getHttpStatus());
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<Response<JwtResponse>> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
		Response<JwtResponse> res = new Response<>();
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
				loginRequest.getPassword());
		final Authentication authentication = authenticationManager.authenticate(authReq);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		res.setBody(new JwtResponse(jwt, userDetails.getUsername(), roles));
		res.setHttpStatus(HttpStatus.OK);
		res.setMessage("ok");
		return new ResponseEntity<Response<JwtResponse>>(res, res.getHttpStatus());
	}

}
