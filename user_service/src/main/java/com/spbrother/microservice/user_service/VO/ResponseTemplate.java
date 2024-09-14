package com.spbrother.microservice.user_service.VO;


import com.spbrother.microservice.user_service.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {
	private UserEntity user;
	private Department department;
}
