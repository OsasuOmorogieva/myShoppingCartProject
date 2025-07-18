package com.example.org.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.org.Repository.UserRepository;

@Service
public class UserService {
@Autowired
UserRepository userRepo;


//@Autowired
//EmailService emailService;

//@Autowired
//PasswordEncoder passwordEncoder;

//public User signup(User user) {
//	user.setUsername(user.getUsername().toLowerCase());
//	user.setEmailId(user.getEmailId().toLowerCase());
//	this.validateUserNameAndEmail(user.getUsername(),user.getEmailId());
//	user.setEmailVerified(false);
//	user.setPassword(user.getPassword());
//	//user.setPassword(passwordEncoder.encode(user.getPassword()));
//	user.setCreatedOn(Timestamp.from(Instant.now()));
//	user.setFirstName(user.getFirstName());
//	user.setLastName(user.getLastName());
//	user.setPhone(user.getPhone());
//	this.userRepo.save(user);
//	this.emailService.sendVerificationEmail(user);
//	return user;
//}

//private void validateUserNameAndEmail(String username, String emailId) {
//	this.userRepo.findByUsername(username).ifPresent(u-> {
//		throw new UserExistException(String.format("Username already exists, %s", u.getUsername()));
//	});
//	this.userRepo.findByEmailId(emailId).ifPresent(u -> {
//		throw new EmailExistException(String.format("Email already exists, %s", u.getEmailId()));
//	});
//}
}
