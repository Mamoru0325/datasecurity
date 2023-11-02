package th.ac.ku.kps.eng.cpe.datasecurity.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import th.ac.ku.kps.eng.cpe.datasecurity.api.response.Response;
import th.ac.ku.kps.eng.cpe.datasecurity.ciphercode.Permutation_Cipher;
import th.ac.ku.kps.eng.cpe.datasecurity.ciphercode.Shift_Cipher;
import th.ac.ku.kps.eng.cpe.datasecurity.ciphercode.Vigenere_Cipher;
import th.ac.ku.kps.eng.cpe.datasecurity.model.Cipher;
import th.ac.ku.kps.eng.cpe.datasecurity.model.Scoreboard;
import th.ac.ku.kps.eng.cpe.datasecurity.model.Type;
import th.ac.ku.kps.eng.cpe.datasecurity.model.User;
import th.ac.ku.kps.eng.cpe.datasecurity.service.CipherService;
import th.ac.ku.kps.eng.cpe.datasecurity.service.ScoreboardService;
import th.ac.ku.kps.eng.cpe.datasecurity.service.TypeService;
import th.ac.ku.kps.eng.cpe.datasecurity.service.UserService;

@RestController
@RequestMapping("/api/cipher")
@CrossOrigin("http://localhost:8081/")
public class CipherRestController {
	@Autowired
	private CipherService cipherService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private UserService userService;
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

	@PostMapping("/precreate")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<?>> preCreate(@RequestBody String question) {
		Response<ObjectNode> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseObject = mapper.createObjectNode();

		try {
			JsonNode jsonNode = mapper.readTree(question);
			int typeId = jsonNode.get("type").asInt();
			Type type = typeService.findById(typeId);
			Cipher cipher = mapper.readValue(question, Cipher.class);
			cipher.setType(type);

			String cipherText = new String();

			if (type.getTypeName().equals("caesar")) {

				Shift_Cipher sc = new Shift_Cipher();
				cipherText = sc.encrypt(cipher.getPlainText(), Integer.parseInt(cipher.getCipherKey()));

			} else if (type.getTypeName().equals("vigenere")) {

				Vigenere_Cipher vc = new Vigenere_Cipher();
				cipherText = vc.encrypt(cipher.getPlainText(), cipher.getCipherKey());

			} else if (type.getTypeName().equals("permutation")) {

				String[] key = cipher.getCipherKey().split(",");

				int[] pi = new int[key.length];

				for (int i = 0; i < key.length; i++) {
					pi[i] = Integer.parseInt(key[i]);
				}

				Permutation_Cipher pc = new Permutation_Cipher(pi);
				cipherText = pc.encrypt(cipher.getPlainText());

			}

			responseObject.put("plainText", cipher.getPlainText());
			responseObject.put("cipherKey", cipher.getCipherKey());
			responseObject.put("level", cipher.getLevel());
			responseObject.put("type", type.getTypeName());
			responseObject.put("cipherText", cipherText);
			res.setBody(responseObject);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {

			res.setMessage(e.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
	}

	@PostMapping("/")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<?>> createQuestion(@RequestBody String question) {
		Response<ObjectNode> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseObject = mapper.createObjectNode();

		try {
			JsonNode jsonNode = mapper.readTree(question);
			int typeId = jsonNode.get("type").asInt();
			Type type = typeService.findById(typeId);
			Cipher cipher = mapper.readValue(question, Cipher.class);
			cipher.setType(type);
			cipher = cipherService.save(cipher);
			responseObject = mapper.valueToTree(cipher);
			responseObject.put("type", type.getTypeName());
			res.setBody(responseObject);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {

			res.setMessage(e.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
	}

	@PutMapping("/")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<?>> updateQuestion(@RequestBody String question) {
		Response<ObjectNode> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseObject = mapper.createObjectNode();

		try {
			JsonNode jsonNode = mapper.readTree(question);
			int typeId = jsonNode.get("type").asInt();
			int cipherId = jsonNode.get("cipherId").asInt();
			Type type = typeService.findById(typeId);
			Cipher cipher = cipherService.findById(cipherId);
			Cipher _cipher = mapper.readValue(question, Cipher.class);
			cipher.clone(_cipher);
			cipher.setType(type);
			cipher = cipherService.save(cipher);
			responseObject = mapper.valueToTree(cipher);
			responseObject.put("type", type.getTypeName());
			res.setBody(responseObject);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {

			res.setMessage(e.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
	}

	@GetMapping("/level/{level}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<List<Cipher>>> findByLevel(@PathVariable("level") String level) {
		Response<List<Cipher>> res = new Response<>();
		try {
			List<Cipher> ciphers = cipherService.findByLevel(level);
			res.setBody(ciphers);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {

			res.setMessage(e.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<Response<List<Cipher>>>(res, res.getHttpStatus());
	}

	@GetMapping("/{id}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<?>> findById(@PathVariable("id") int id) {
		Response<ObjectNode> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseObject = mapper.createObjectNode();
		try {
			Cipher cipher = cipherService.findById(id);
			responseObject = mapper.valueToTree(cipher);
			responseObject.put("type", cipher.getType().getTypeName());
			res.setBody(responseObject);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {

			res.setMessage(e.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
	}

	@DeleteMapping("/{id}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<String>> deleteById(@PathVariable("id") int id) {
		Response<String> res = new Response<>();
		try {
			cipherService.deleteById(id);
			res.setBody("delete cipher" + id + "complete");
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {

			res.setMessage(e.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
	}

	@GetMapping("/question/{level}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('User') or hasRole('Admin')")
	public ResponseEntity<Response<List<?>>> find10RandomQuestion(@PathVariable("level") String level) {
		Response<List<?>> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userService.findByUserName(username);
			Scoreboard scoreboard = new Scoreboard(user, level, 0);
			scoreboard = scoreboardService.save(scoreboard);
			List<Cipher> ciphers = cipherService.find10RandomQuestion(level);

			for (Cipher cipher : ciphers) {
				String cipherText = new String();
				ObjectNode responObject = mapper.createObjectNode();
				responObject.put("cipherId", cipher.getCipherId());
				responObject.put("scoreboardId", scoreboard.getScoreId());
				responObject.put("type", cipher.getType().getTypeName());
				responObject.put("level", cipher.getLevel());
				if (cipher.getType().getTypeName().equals("caesar")) {

					Shift_Cipher sc = new Shift_Cipher();
					cipherText = sc.encrypt(cipher.getPlainText(), Integer.parseInt(cipher.getCipherKey()));

				} else if (cipher.getType().getTypeName().equals("vigenere")) {

					Vigenere_Cipher vc = new Vigenere_Cipher();
					cipherText = vc.encrypt(cipher.getPlainText(), cipher.getCipherKey());

				} else if (cipher.getType().getTypeName().equals("permutation")) {

					String[] key = cipher.getCipherKey().split(",");

					int[] pi = new int[key.length];

					for (int i = 0; i < key.length; i++) {
						pi[i] = Integer.parseInt(key[i]);
					}

					Permutation_Cipher pc = new Permutation_Cipher(pi);
					cipherText = pc.encrypt(cipher.getPlainText());

				}
				responObject.put("cipherText", cipherText);

				responseList.add(responObject);
			}

			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
	}

	@GetMapping("/{level}/{typeId}")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Response<List<Cipher>>> findByLevelAndType(@PathVariable("level") String level,
			@PathVariable("typeId") int typeId) {
		Response<List<Cipher>> res = new Response<List<Cipher>>();
		try {
			Type type = typeService.findById(typeId);
			List<Cipher> ciphers = cipherService.findByLevelAndType(level, type);
			res.setBody(ciphers);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {
			res.setBody(null);
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Response<List<Cipher>>>(res, res.getHttpStatus());
	}

}
