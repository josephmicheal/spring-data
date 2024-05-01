package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>,CustomStudentRepository {

    @Procedure(procedureName = "student_pkg.InsertStudentDetails")
    Integer insertStudentDetails1(
            @Param("p_name") String name,
            @Param("p_address") String address,
            @Param("p_course") String course,
            @Param("p_department_id") Integer departmentId
    );

    @Query(value = "SELECT ConvertStringToDate(:dateString) FROM DUAL", nativeQuery = true)
    Date getDateFromString(@Param("dateString") String dateString);


}

