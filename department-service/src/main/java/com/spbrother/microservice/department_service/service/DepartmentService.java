package com.spbrother.microservice.department_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spbrother.microservice.department_service.entity.Department;
import com.spbrother.microservice.department_service.repository.DepartmentRepository;

@Service 
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department getDepartmentById(Long deptId) {
		// TODO Auto-generated method stub
		return departmentRepository.findByDeptId(deptId);
	}

	

}
