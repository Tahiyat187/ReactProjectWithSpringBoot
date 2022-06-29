package com.example.handsOnProject2.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.example.handsOnProject2.exception.StudentAlreadyExistsException;
import com.example.handsOnProject2.model.Student;
import com.example.handsOnProject2.model.StudentView;

@Repository
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Object> findAll(){
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(GrantedAuthority g: authorities) {
			System.out.println(g.getAuthority());
			if(g.getAuthority().equals("ROLE_ADMIN")) {
				return studentRepository.findAllForAdmin();
			}
			else if(g.getAuthority().equals("ROLE_STUDENT")) {
				return studentRepository.findAllForStudent();
			}
		}
		return null;
	}
	
	public Student findById(Long id) {
		return studentRepository.findById(id).get();
	}
	
	public Student addStudent(Student student) throws StudentAlreadyExistsException {
		if(student.getId() != null) {
			Optional<Student> studentExists = studentRepository.findById(student.getId());
			if(studentExists.isPresent()) {
				throw new StudentAlreadyExistsException();
			}
		}
		return studentRepository.save(student);
	}
	
	public void update(Student student) {
		System.out.println(student);
		studentRepository.save(student);
	}
	public void deleteById(Long id) {
		studentRepository.deleteById(id);
	}
	
	public Student findByName(String name) {
		return studentRepository.findByName(name);
	}
}
