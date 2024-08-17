package com.acintyo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acintyo.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,String>{
	
	Optional<Admin> findByUserNameAndPassword(String userName,String password);

}
