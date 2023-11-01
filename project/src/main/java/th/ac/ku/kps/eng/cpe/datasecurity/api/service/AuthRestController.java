package th.ac.ku.kps.eng.cpe.datasecurity.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import th.ac.ku.kps.eng.cpe.datasecurity.api.response.Response;
import th.ac.ku.kps.eng.cpe.datasecurity.model.Refreshtoken;
import th.ac.ku.kps.eng.cpe.datasecurity.model.User;
import th.ac.ku.kps.eng.cpe.datasecurity.model.dto.LoginDTO;
import th.ac.ku.kps.eng.cpe.datasecurity.model.dto.SignupDTO;
import th.ac.ku.kps.eng.cpe.datasecurity.model.dto.TokenRefreshRequest;
import th.ac.ku.kps.eng.cpe.datasecurity.model.dto.TokenRefreshResponse;
import th.ac.ku.kps.eng.cpe.datasecurity.security.exception.AccountAlreadyExistException;
import th.ac.ku.kps.eng.cpe.datasecurity.security.jwt.JwtUtils;
import th.ac.ku.kps.eng.cpe.datasecurity.security.jwt.response.MessageResponse;
import th.ac.ku.kps.eng.cpe.datasecurity.security.jwt.response.JwtResponse;
import th.ac.ku.kps.eng.cpe.datasecurity.security.service.UserDetailsImpl;
import th.ac.ku.kps.eng.cpe.datasecurity.service.RefreshtokenService;
import th.ac.ku.kps.eng.cpe.datasecurity.service.UserService;
import th.ac.ku.kps.eng.cpe.datasecurity.util.TokenProvider;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:8081/")
public class AuthRestController {
	@Autowired
	private UserService userService;
	@Autowired
	private RefreshtokenService refreshtokenService;
	@Autowired
	private TokenProvider jwtTokenUtil;
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
	public ResponseEntity<Response<User>> registerUser(@Valid @RequestBody SignupDTO user) {
		Response<User> res = new Response<>();
		try {
			String roleName = "ROLE_User";
			User _user = new User();
			_user.signup(user);
			_user.setPassword(encoder.encode(_user.getPassword()));
			_user.setStatus("yes");
			User __user = userService.registerNewAccount(_user, roleName);
			res.setMessage("Register Success");
			res.setBody(__user);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		} catch (AccountAlreadyExistException uaeEx) {
			res.setMessage("An account for that username/email already exists.");
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		}
	}

	@PostMapping("/signup/staff")
	public ResponseEntity<Response<User>> registerStaff(@Valid @RequestBody SignupDTO user) {
		Response<User> res = new Response<>();
		try {
			String roleName = "ROLE_Admin";
			User _user = new User();
			_user.signup(user);
			_user.setPassword(encoder.encode(_user.getPassword()));
			_user.setStatus("no");
			User __user = userService.registerNewAccount(_user, roleName);
			res.setMessage("Register Success");
			res.setBody(__user);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		} catch (AccountAlreadyExistException uaeEx) {
			res.setMessage("An account for that username/email already exists.");
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setMessage(ex.getLocalizedMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		}
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
		User user = userService.findByUserName(loginRequest.getUsername());
		if (user.getStatus().equals("no")) {
			Response<User> res = new Response<>();
			res.setMessage("You don't have AUTHORIZED");
			res.setHttpStatus(HttpStatus.UNAUTHORIZED);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		System.out.println(jwt);
		
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		Refreshtoken refreshToken = refreshtokenService.createRefreshToken(userDetails.getId());

		return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
				userDetails.getName(), userDetails.getUsername(), roles));
	}

	@PostMapping("/signout")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('User') or hasRole('Admin')")
	public ResponseEntity<?> logoutUser() {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		refreshtokenService.deleteByUserId(userDetails.getId());
		return ResponseEntity.ok(new MessageResponse("Log out successful!"));
	}

	@PostMapping("/changepassword")
	public ResponseEntity<Response<String>> changePassword(@RequestBody String rawLogin) {
		Response<String> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonNode = mapper.readTree(rawLogin);
			String email = jsonNode.get("email").asText();
			System.out.println(email);
			String password = jsonNode.get("password").asText();
			System.out.println(password);
			if (userService.userNameExists(email)) {
				User user = userService.findByUserName(email);
				user.setPassword(encoder.encode(password));
				user = userService.save(user);
				res.setBody("Change Password Complete");
				res.setHttpStatus(HttpStatus.OK);
				return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
			}
			res.setBody("Don't Have This Email");
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.getRefreshToken();
		Response<TokenRefreshResponse> resp = new Response();

		resp.setMessage(requestRefreshToken + " Refresh token is not in database!");

		resp.setHttpStatus(HttpStatus.NOT_FOUND);

		return refreshtokenService.findByToken(requestRefreshToken).map(refreshtokenService::verifyExpiration)
				.map(Refreshtoken::getUser).map(user -> {
					Set<SimpleGrantedAuthority> authorities = new HashSet<>();

					final Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null,
							authorities);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					User _user = new User();
					_user.setUsername(user.getUsername());
					String token = jwtTokenUtil.generateToken(authentication);
//	          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
//	          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshtoken));
					resp.setBody(new TokenRefreshResponse(_user, token, requestRefreshToken));

					resp.setMessage("Token refresh");

					resp.setHttpStatus(HttpStatus.OK);

					return new ResponseEntity<Response<TokenRefreshResponse>>(resp, resp.getHttpStatus());

				}).orElse(new ResponseEntity<Response<TokenRefreshResponse>>(resp, resp.getHttpStatus()));
	}

}
