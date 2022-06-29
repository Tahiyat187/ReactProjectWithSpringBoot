package com.example.handsOnProject2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name ="name",nullable=false,unique=true)
	private String name;
	private Long age;
	private String address;
	private String password;
	private String role;
	
//	public Student(Long id, String name, Long age, String address, String password, String role) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.age = age;
//		this.address = address;
//		this.password = password;
//		this.role = role;
//	}
	
	
}
