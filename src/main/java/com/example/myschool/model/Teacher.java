package com.example.myschool.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "teacher")
public class Teacher {

	
	@Id //the primary key for the entity
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
    @OneToMany(mappedBy="teacher", cascade = {CascadeType.ALL})
    private List<Student> students;

    //Constructors
    
	public Teacher() {
		
	}

	public Teacher(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
    
	
	
	
	
	
}
