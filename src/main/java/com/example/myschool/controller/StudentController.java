package com.example.myschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myschool.model.Student;
import com.example.myschool.repository.StudentRepository;
import com.example.myschool.repository.TeacherRepository;

@RestController
@RequestMapping(path="/api/v1")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    
    @GetMapping("/teachers/{teacherId}/students")
    public List < Student > getStudentsByTeacher(@PathVariable(value = "teacherId") int teacherId) {
        return studentRepository.findByTeacherId(teacherId);
    }
    
    
    @PostMapping("/teachers/{teacherId}/students")
    public Student createStudent(@PathVariable(value = "teacherId") int teacherId, @Validated @RequestBody Student student) throws ResourceNotFoundException {
        return teacherRepository.findById(teacherId).map(teacher -> {
            student.setTeacher(teacher);
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
    }
    
    
    @PutMapping("/teachers/{teacherId}/students/{studentId}")
    public Student updateStudent(@PathVariable(value = "teacherId")int teacherId,@PathVariable(value = "studentId") int studentId, @Validated @RequestBody Student studentRequest)
    throws ResourceNotFoundException {
        if (!teacherRepository.existsById(teacherId)) {
            throw new ResourceNotFoundException("teacherId not found");
        }

        return studentRepository.findById(studentId).map(student -> {
            student.setName(studentRequest.getName());
            student.setEmail(studentRequest.getEmail());
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("student id not found"));
    }
	
    
    
    @DeleteMapping("/teachers/{teacherId}/students/{studentId}")
    public ResponseEntity < ? > deleteStudent(@PathVariable(value = "teacherId") int teacherId,@PathVariable(value = "studentId") int studentId) throws ResourceNotFoundException {
        return studentRepository.findByIdAndTeacherId(studentId, teacherId).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
            "Student not found with id " + studentId + " and teacherId " + teacherId));
    }
}
