package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomStudentRepositoryImpl implements CustomStudentRepository {

    private final EntityManager entityManager;

    @Override
    public List<Student> getStudentsByDepartment1(int departmentId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("student_pkg.GetStudentsByDepartment", Student.class)
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, void.class, ParameterMode.REF_CURSOR)
                .setParameter(1, departmentId);

        query.execute();
        return query.getResultList();
    }

    @Override
    public List<Student> getStudentsByDepartment(int departmentId) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("Student.getStudentsByDepartment");
        query.setParameter("p_department_id", departmentId);
        query.execute();
        return query.getResultList();
    }

    public List<StudentDepartment> getStudentDetails1() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("student_pkg.GetStudentDetails");
        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
        query.execute();

        List<Object[]> results = query.getResultList();
        List<StudentDepartment> studentDepartments = new ArrayList<>();
        for (Object[] result : results) {
            StudentDepartment sd = new StudentDepartment();
            sd.setStudentId(((BigDecimal) result[0]).intValue());
            sd.setStudentName((String) result[1]);
            sd.setDepartmentName((String) result[2]);
            studentDepartments.add(sd);
        }
        return studentDepartments;
    }

    public List<StudentDepartment> getStudentDetails() {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("GetStudentDetails");
        query.execute();
        return query.getResultList();
    }

    public Integer insertStudentDetails(String name, String address, String course, Integer departmentId) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("Student.insertStudentDetails");
        query.setParameter("p_name", name);
        query.setParameter("p_address", address);
        query.setParameter("p_course", course);
        query.setParameter("p_department_id", departmentId);

        query.execute();
        return (Integer) query.getOutputParameterValue("p_student_id");
    }
}
