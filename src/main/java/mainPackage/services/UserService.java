package mainPackage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mainPackage.entities.user.User;
import mainPackage.entities.user.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
}
