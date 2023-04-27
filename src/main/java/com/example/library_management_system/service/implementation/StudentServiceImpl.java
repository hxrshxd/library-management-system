package com.example.library_management_system.service.implementation;

import com.example.library_management_system.DTOs.RequestDtos.StudentRequestDto;
import com.example.library_management_system.DTOs.RequestDtos.StudentUpdateMobNoRequestDto;
import com.example.library_management_system.DTOs.ResponseDtos.StudentIdResponseDto;
import com.example.library_management_system.DTOs.ResponseDtos.StudentUpdateMobNoResponseDto;
import com.example.library_management_system.entity.Card;
import com.example.library_management_system.entity.Student;
import com.example.library_management_system.enums.Status;
import com.example.library_management_system.exceptions.StudentNotFoundException;
import com.example.library_management_system.repository.StudentRepository;
import com.example.library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {

        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobNo(studentRequestDto.getMobNo());

        // creating card and linking that card to student
        Card card = new Card();
        card.setStatus(Status.valueOf("ACTIVATED"));
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 365);
        Date date = cal.getTime();
        card.setValidTill(date);
        card.setStudent(student);

        // adding student to db
        student.setCard(card);
        studentRepository.save(student);
        return "Student added successfully";
    }

    @Override
    public String deleteStudentById(int id) {
        if (!studentRepository.existsById(id)) return "Student not in DataBase";

        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }

    @Override
    public StudentIdResponseDto findStudentById(int id) throws StudentNotFoundException {

        try {
            Student student = studentRepository.findById(id).get();

            StudentIdResponseDto studentIdResponseDto = new StudentIdResponseDto();
            studentIdResponseDto.setAge(student.getAge());
            studentIdResponseDto.setName(student.getName());
            studentIdResponseDto.setDepartment(student.getDepartment());
            studentIdResponseDto.setMobNo(student.getMobNo());

            return studentIdResponseDto;
        }
        catch (Exception e) {
            throw new StudentNotFoundException("Student Not found");
        }
    }

    @Override
    public List<Student> findAllStudent() {
        List<Student> list =  studentRepository.findAll();
        return list;
    }

    @Override
    public StudentUpdateMobNoResponseDto updateStudentMobNo(StudentUpdateMobNoRequestDto studentUpdateMobNoRequestDto) throws StudentNotFoundException {

        try {
            // get student by id, update mobNo and save in db
            Student student = studentRepository.findById(studentUpdateMobNoRequestDto.getId()).get();
            student.setMobNo(studentUpdateMobNoRequestDto.getMobNo());
            studentRepository.save(student);

            // create object dto to return name and mobNo
            StudentUpdateMobNoResponseDto studentUpdateMobNoResponseDto = new StudentUpdateMobNoResponseDto();
            studentUpdateMobNoResponseDto.setName(student.getName());
            studentUpdateMobNoResponseDto.setMobNo(student.getMobNo());

            return studentUpdateMobNoResponseDto;
        }
        catch (Exception e) {
            throw new StudentNotFoundException("Student not found");
        }
    }
}
