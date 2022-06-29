package com.example.handsOnProject2.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.handsOnProject2.model.Student;
import com.example.handsOnProject2.repository.StudentService;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	StudentService studentService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student;
		try {
			student = studentService.findByName(username);
		}catch(Exception ex) {
			throw new UsernameNotFoundException(username);
		}
		
		ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(student.getRole()));
		// TODO Auto-generated method stub
		return new User(student.getName(),student.getPassword(),roles);
	}

}
