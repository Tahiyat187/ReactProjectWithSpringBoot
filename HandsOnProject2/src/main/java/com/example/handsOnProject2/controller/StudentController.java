package com.example.handsOnProject2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.handsOnProject2.exception.StudentAlreadyExistsException;
import com.example.handsOnProject2.model.Student;
import com.example.handsOnProject2.model.StudentView;
import com.example.handsOnProject2.repository.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@PostMapping("/register")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> registerStudent(@RequestBody Student student) throws Exception{
		try {
			studentService.addStudent(student);
		}catch(Exception ex) {
			throw ex;
		}
		
		return ResponseEntity.ok(new String("succeed"));
	}
	
	@GetMapping("/findAll")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> findAll(){
		return new ResponseEntity<>(studentService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/search")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> findStudentByName(@RequestParam String name){
		Student student = studentService.findByName(name);
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/profile")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> profile(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Student student = studentService.findByName(auth.getName());
		return ResponseEntity.ok(student);
	}
	
	@PutMapping("/update")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> update(@RequestBody Student student){
		studentService.update(student);
		return ResponseEntity.ok(null);
	}
}
