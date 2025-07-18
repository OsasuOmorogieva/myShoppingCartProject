package com.example.org.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.org.Service.UserService;
import com.example.org.modal.Users;

@RestController
@RequestMapping
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping("/products/signup")
	public ResponseEntity<Users> signup(Users user){
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

}
