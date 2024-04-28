package com.example.demo;

import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test") // Ensure to use the test profile that configures the H2 database
@RequiredArgsConstructor
public class StudentRepositoryTests {

    private final TestEntityManager entityManager;

    @Test
    public void testGetStudentsByDepartment() {
        // Setup - create and persist a department and some students
        Department department = new Department();
        department.setName("Computer Science");
        department.setFaculty("Engineering");
        department = entityManager.persist(department);

        Student student1 = new Student();
        student1.setName("Alice");
        student1.setAddress("1234 Street");
        student1.setCourse("CS101");
        student1.setDepartment(department);
        entityManager.persist(student1);

        Student student2 = new Student();
        student2.setName("Bob");
        student2.setAddress("5678 Drive");
        student2.setCourse("CS102");
        student2.setDepartment(department);
        entityManager.persist(student2);

        entityManager.flush(); // Flush changes to ensure all data is committed before the test.

        // Execute stored procedure via EntityManager
        StoredProcedureQuery query = entityManager.getEntityManager().createStoredProcedureQuery("GetStudentsByDepartment", Student.class);
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Class.class, ParameterMode.REF_CURSOR);
        query.setParameter(1, department.getId());

        query.execute();

        List<Student> results = query.getResultList();

        // Assertions - validate the results
        Assertions.assertEquals(2, results.size(), "Should find two students in the Computer Science department.");
        Assertions.assertTrue(results.stream().anyMatch(s -> s.getName().equals("Alice")), "Results should include Alice.");
        Assertions.assertTrue(results.stream().anyMatch(s -> s.getName().equals("Bob")), "Results should include Bob.");
    }
}
