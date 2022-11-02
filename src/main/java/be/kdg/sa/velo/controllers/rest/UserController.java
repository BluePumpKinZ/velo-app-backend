package be.kdg.sa.velo.controllers.rest;

import be.kdg.sa.velo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping (path = "/api/v1/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController (UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping (path="/validSimulatorIds")
	public ResponseEntity<List<Integer>> getValidSimulatorIds () {
		return ResponseEntity.ok (userService.getValidSimulatorIds ());
	}
}
