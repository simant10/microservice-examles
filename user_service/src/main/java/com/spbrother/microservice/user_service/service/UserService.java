package com.spbrother.microservice.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spbrother.microservice.user_service.VO.Department;
import com.spbrother.microservice.user_service.VO.ResponseTemplate;

import com.spbrother.microservice.user_service.entity.UserEntity;
import com.spbrother.microservice.user_service.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RestTemplate restTemplate;
	
	public UserEntity saveUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}
	
	public ResponseTemplate getUserById(Long userId) {
		ResponseTemplate respTemp = new ResponseTemplate();
		UserEntity user= userRepository.findByUserId(userId);
		Department department = restTemplate.getForObject("http://localhost:9001/department/"+user.getDeptId()
		, Department.class);
		respTemp.setUser(user);
		respTemp.setDepartment(department);
		return respTemp;
		
	}

}
