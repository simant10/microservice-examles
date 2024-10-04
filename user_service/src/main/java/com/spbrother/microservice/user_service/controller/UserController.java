package com.spbrother.microservice.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spbrother.microservice.user_service.VO.ResponseTemplate;
import com.spbrother.microservice.user_service.entity.UserEntity;
import com.spbrother.microservice.user_service.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public UserEntity saveUser(@RequestBody UserEntity userEntity) {
		return userService.saveUser(userEntity);
	}
	/*@GetMapping("/{id}")
	public User getUser(@PathVariable("id") Long userId) {
		return userService.getUserById(userId);
	}*/
	@GetMapping("/{id}")
	public ResponseTemplate getUserWithDepartment(@PathVariable("id") Long userId) {
		return userService.getUserById(userId);
	}

}
