package com.example.library_management_system.service;

import com.example.library_management_system.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    public String addStudent(Student student);

    public String deleteStudentById(int id) throws Exception;

    public Optional<Student> findStudentById(int id) throws Exception;

    public List<Student> findAllStudent();
}
