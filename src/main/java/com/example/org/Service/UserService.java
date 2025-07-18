package com.example.org.Service;


import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.org.Repository.UserRepository;
import com.example.org.modal.Users;
import com.example.org.security.JWTService;

@Service
public class UserService {
@Autowired
UserRepository userRepo;

@Autowired
AuthenticationManager authManager;

@Autowired
JWTService jwtService;


//@Autowired
//EmailService emailService;


private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

public Users signup(Users user) {
	user.setUsername(user.getUsername().toLowerCase());
	user.setEmailId(user.getEmailId().toLowerCase());
	//this.validateUserNameAndEmail(user.getUsername(),user.getEmailId());
	user.setEmailVerified(false);
	user.setPassword(user.getPassword());
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	user.setCreatedOn(Timestamp.from(Instant.now()));
	user.setFirstName(user.getFirstName());
	user.setLastName(user.getLastName());
	user.setPhone(user.getPhone());
	userRepo.save(user);
	//this.emailService.sendVerificationEmail(user);
	return user;
}

public String verify(Users user) {
	Authentication authentication =
			authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	if(authentication.isAuthenticated()) {
	return jwtService.generateToken(user.getUsername());
	}
	return "unable to authenticate User.";
}

//private void validateUserNameAndEmail(String username, String emailId) {
//	this.userRepo.findByUsername(username).ifPresent(u-> {
//		throw new UserExistException(String.format("Username already exists, %s", u.getUsername()));
//	});
//	this.userRepo.findByEmailId(emailId).ifPresent(u -> {
//		throw new EmailExistException(String.format("Email already exists, %s", u.getEmailId()));
//	});
//}
}
