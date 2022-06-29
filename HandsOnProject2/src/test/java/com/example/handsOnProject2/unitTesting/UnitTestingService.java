package com.example.handsOnProject2.unitTesting;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.handsOnProject2.exception.StudentAlreadyExistsException;
import com.example.handsOnProject2.model.Student;
import com.example.handsOnProject2.repository.StudentService;
import com.example.handsOnProject2.repository.StudentRepository;


public class UnitTestingService {
	
	private StudentService studentDao;
	
	private StudentRepository studentRepository;
	
	@BeforeEach
	public void setup() {
		studentRepository = Mockito.mock(StudentRepository.class);
		studentDao = new StudentService(studentRepository);
	}
	
	@Test
	public void testRegisterStudent() throws StudentAlreadyExistsException {
		Student student = new Student(Long.parseLong("7"),"somoy",Long.parseLong("23"),"dhaka","1234","ROLE_STUDENT");
		when(studentRepository.save(student)).thenReturn(student);
		assertEquals(student,studentDao.addStudent(student));
	}
	
	@Test
	public void testFindById() {
		Optional<Student> student = Optional.of(new Student(Long.parseLong("7"),"somoy",Long.parseLong("23"),"dhaka","1234","ROLE_STUDENT"));
		when(studentRepository.findById(Long.parseLong("1"))).thenReturn(student);
		assertEquals(studentDao.findById(Long.parseLong("1")),student.get());
	}

}
