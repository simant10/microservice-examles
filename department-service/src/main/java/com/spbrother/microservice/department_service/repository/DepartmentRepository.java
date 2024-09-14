package com.spbrother.microservice.department_service.repository;


import org.springframework.stereotype.Repository;

import com.spbrother.microservice.department_service.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Department findByDeptId(Long deptId);

	//Department findByDepartmentId(Long deptId);

}
