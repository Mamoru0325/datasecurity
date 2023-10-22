package th.ac.ku.kps.eng.cpe.datasecurity.api.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin("http://localhost:8081/")
@RestController
@RequestMapping("/api/test")
public class TestRestController {
	
	 @GetMapping("/all")
	  public String allAccess() {
	    return "Public Content.";
	  }

	  @GetMapping("/user")
	  @SecurityRequirement(name = "Bearer Authentication")
	  @PreAuthorize("hasRole('User') or hasRole('Admin')")
	  public String userAccess() {
	    return "User Content.";
	  }

	  @GetMapping("/admin")
	  @SecurityRequirement(name = "Bearer Authentication")
	  @PreAuthorize("hasRole('Admin')")
	  public String adminAccess() {
	    return "Admin Board.";
	  }

}
