package com.example.library_management_system.service.implementation;

import com.example.library_management_system.entity.Card;
import com.example.library_management_system.entity.Student;
import com.example.library_management_system.enums.Status;
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
    public String addStudent(Student student) {

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
    public String deleteStudentById(int id) throws Exception {
        if (!studentRepository.existsById(id)) throw new Exception("Student not in DataBase");

        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }

    @Override
    public Optional<Student> findStudentById(int id) {
        return Optional.of(studentRepository.findById(id).get());
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }
}
