package com.example.myschool.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

import com.example.myschool.model.Teacher;
import com.example.myschool.repository.TeacherRepository;

@RestController
@RequestMapping(path="/api/v1")
public class TeacherController {

	@Autowired 
	private TeacherRepository teacherRepository;
	
    @GetMapping("/teachers")
    public List < Teacher > getInstructors() {
        return teacherRepository.findAll();
    }
	
    @GetMapping("/teachers/{id}")
    public ResponseEntity < Teacher > getTeacherById(@PathVariable(value = "id") int teacherId) throws ResourceNotFoundException {
        Teacher user = teacherRepository.findById(teacherId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + teacherId));
        return ResponseEntity.ok().body(user);
    }
    
    @DeleteMapping("/teachers/{id}")
    public Map < String, Boolean > deleteUser(@PathVariable(value = "id") int teacherId) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + teacherId));

        teacherRepository.delete(teacher);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    @PutMapping("/teachers/{id}")
    public ResponseEntity < Teacher > updateUser(@PathVariable(value = "id") int teacherId, @Validated @RequestBody Teacher userDetails) throws ResourceNotFoundException {
        Teacher user = teacherRepository.findById(teacherId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + teacherId));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        final Teacher updatedUser = teacherRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    
    @PostMapping("/teachers")
    public Teacher createUser(@Validated @RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
