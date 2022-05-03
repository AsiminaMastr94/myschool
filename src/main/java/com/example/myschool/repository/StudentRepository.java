package com.example.myschool.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myschool.model.Student;

@Repository
public interface StudentRepository extends  JpaRepository<Student,Integer>{

	List<Student> findByTeacherId(int teacherId);
	Optional<Student> findByIdAndTeacherId(int id, int teacherId);


}
