package com.example.org.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.org.Service.UserService;
import com.example.org.modal.Users;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserService service;
	
	@PostMapping("/signup")
	public ResponseEntity<Users> signup(@RequestBody Users user){
		Users newUser = service.signup(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public String login(@RequestBody Users user) {
			return service.login(user);
		}
	
@GetMapping("verify/email")
public ResponseEntity<?> verifyEmail() {
    logger.debug("Verifying Email");
	this.service.verifyEmail();
	return new ResponseEntity<>(HttpStatus.OK);
}
 @PostMapping("/resetPassword")
public ResponseEntity<?> resetPassword(@RequestBody JsonNode json){
	logger.debug("Reseting Password");
	this.service.resetPassword(json.get("password").asText());
	return new ResponseEntity<>(HttpStatus.OK);
}
 
 @GetMapping("/reset/{emailId}")
 public ResponseEntity<?> sendResetPasswordEmail(@PathVariable String emailId){
	 logger.debug("sending reset password Email, emailId: {}", emailId);
	 this.service.sendResetPasswordEmail(emailId);
	 return new ResponseEntity<>(HttpStatus.OK);
	 
 }
	}

