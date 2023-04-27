package com.example.library_management_system.service;

import com.example.library_management_system.DTOs.RequestDtos.StudentRequestDto;
import com.example.library_management_system.DTOs.RequestDtos.StudentUpdateMobNoRequestDto;
import com.example.library_management_system.DTOs.ResponseDtos.StudentIdResponseDto;
import com.example.library_management_system.DTOs.ResponseDtos.StudentUpdateMobNoResponseDto;
import com.example.library_management_system.entity.Student;
import com.example.library_management_system.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    public String addStudent(StudentRequestDto studentRequestDto);

    public String deleteStudentById(int id);

    public StudentIdResponseDto findStudentById(int id) throws StudentNotFoundException;

    public List<Student> findAllStudent();

    public StudentUpdateMobNoResponseDto updateStudentMobNo(StudentUpdateMobNoRequestDto studentUpdateMobNoRequestDto) throws StudentNotFoundException;
}
