package com.example.myschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myschool.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer>{
 //Auto-implemented from spring
}
