package com.example.demo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CustomDateRepository customDateRepository;

    // Retrieve all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Retrieve a single student by ID
    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    // Create or update a student
    public Student saveOrUpdateStudent(Student student) {
        return studentRepository.save(student);
    }

    // Delete a student
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByDepartment(int departmentId) {
        return studentRepository.getStudentsByDepartment(departmentId);
    }

    public List<StudentVO> getStudentsByDepartmentFunction(int departmentId) {
        return studentRepository.getStudentsByDepartmentFunction(departmentId);
    }

    public Date getDateFromString(String dateString){
        return customDateRepository.getDateFromString(dateString);
    }

    public Integer convertStringToNumber(String inputString) {
        return customDateRepository.convertStringToNumber(inputString);
    }

    public List<StudentDepartment> getStudentDetails() {
        return studentRepository.getStudentDetails();
    }

    @Transactional
    public Integer addStudent(String name, String address, String course, Integer departmentId) {
        try {
            return studentRepository.insertStudentDetails(name, address, course, departmentId);
        } catch (Exception e) {
            // Log and handle the exception appropriately
            throw new RuntimeException("Error inserting student details", e);
        }
    }
}
