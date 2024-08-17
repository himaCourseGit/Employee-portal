package com.acintyo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acintyo.Entity.ManagingEmployeeDirectory;

public interface IManagingEmoployeeDirectoryRepository extends JpaRepository<ManagingEmployeeDirectory, String>{
	Optional<ManagingEmployeeDirectory> findByEmployeeId(String employeeId);

}
