package com.example.handsOnProject2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.handsOnProject2.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	public Student findByName(String name); 

	@Query(value = "SELECT id,name,address,age from student",nativeQuery=true)
	public List<Object> findAllForAdmin();
	
	@Query(value = "SELECT id,name,address from student",nativeQuery=true)
	public List<Object> findAllForStudent();
}
