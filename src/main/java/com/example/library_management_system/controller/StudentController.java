package com.example.library_management_system.controller;

import com.example.library_management_system.DTOs.RequestDtos.StudentRequestDto;
import com.example.library_management_system.DTOs.RequestDtos.StudentUpdateMobNoRequestDto;
import com.example.library_management_system.DTOs.ResponseDtos.StudentIdResponseDto;
import com.example.library_management_system.DTOs.ResponseDtos.StudentUpdateMobNoResponseDto;
import com.example.library_management_system.entity.Student;
import com.example.library_management_system.exceptions.StudentNotFoundException;
import com.example.library_management_system.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return studentService.addStudent(studentRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable("id") int id){
        return studentService.deleteStudentById(id);
    }

    @GetMapping("/get/{id}")
    public StudentIdResponseDto getStudentById(@PathVariable("id") int id) throws Exception {
        return studentService.findStudentById(id);
    }

    @GetMapping("/get/all-students")
    public List<Student> getAllStudents() {
        return studentService.findAllStudent();
    }

    @PutMapping("/update-mobNo")
    public StudentUpdateMobNoResponseDto updateStudentMobNo(@RequestBody StudentUpdateMobNoRequestDto studentUpdateMobNoRequestDto) throws StudentNotFoundException {
        return studentService.updateStudentMobNo(studentUpdateMobNoRequestDto);
    }

}
