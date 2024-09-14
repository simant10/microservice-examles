package com.spbrother.microservice.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.spbrother.microservice.user_service.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUserId(Long userId);

}
