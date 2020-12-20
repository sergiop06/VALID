package com.valid.backend.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valid.backend.model.User;
import com.valid.backend.services.UserService;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;
	
	final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@PostMapping("/users")
	public ResponseEntity<?> addNewUser(@RequestBody User newUser) {
		logger.info("creating user with full name : " + newUser.getFirstName()+" "+newUser.getLastName());
		
		User userCreated = userService.create(newUser);
		return new ResponseEntity<>(userCreated.getId(), HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/users")
	public @ResponseBody Iterable<User> getAllUsers() {
		logger.info("=== retrieving users ====  " );
		return userService.findAllUsers();
	}
	
	@PutMapping(path = "/users/process")
	public ResponseEntity<?> processUsers(@RequestBody List<Long> userIds) {
		logger.info("=== processing users ====  " );
		userService.processUsers(userIds);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	
}
