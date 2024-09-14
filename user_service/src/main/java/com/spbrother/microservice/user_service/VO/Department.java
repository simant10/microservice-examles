package com.spbrother.microservice.user_service.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	private long deptId;
	private String deptCode;
	private String deptName;
	private String deptaddress;
}
