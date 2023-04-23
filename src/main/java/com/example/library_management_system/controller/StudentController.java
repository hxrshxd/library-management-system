package com.example.library_management_system.controller;

import com.example.library_management_system.entity.Student;
import com.example.library_management_system.service.StudentService;
import com.example.library_management_system.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable("id") int id) throws Exception {
        return studentService.deleteStudentById(id);
    }

    @GetMapping("/get/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") int id) throws Exception {
        return studentService.findStudentById(id);
    }

    @GetMapping("/get/all-students")
    public List<Student> getAllStudents() {
        return studentService.findAllStudent();
    }
}
