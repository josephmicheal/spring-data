package com.example.demo;

import java.util.Date;
import java.util.List;

public interface CustomStudentRepository {
    List<Student> getStudentsByDepartment(int departmentId);

    List<Student> getStudentsByDepartment1(int departmentId);

    List<StudentDepartment> getStudentDetails();

    Integer insertStudentDetails(String name, String address, String course, Integer departmentId);

    List<StudentVO> getStudentsByDepartmentFunction(int departmentId);
}
