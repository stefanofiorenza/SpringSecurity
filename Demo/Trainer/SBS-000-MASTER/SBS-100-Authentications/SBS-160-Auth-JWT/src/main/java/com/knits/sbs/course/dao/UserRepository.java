package com.knits.sbs.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knits.sbs.course.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);
}