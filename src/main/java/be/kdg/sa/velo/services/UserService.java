package be.kdg.sa.velo.services;

import be.kdg.sa.velo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<Integer> getValidSimulatorIds () {
		return userRepository.getValidSimulatorIds ();
	}
}
