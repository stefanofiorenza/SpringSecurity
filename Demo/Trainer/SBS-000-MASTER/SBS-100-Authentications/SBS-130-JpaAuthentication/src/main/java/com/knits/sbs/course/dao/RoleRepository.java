package com.knits.sbs.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knits.sbs.course.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	
}