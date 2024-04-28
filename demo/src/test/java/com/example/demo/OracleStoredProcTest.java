package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
public class OracleStoredProcTest {

    private static final DockerImageName ORACLE_IMAGE = DockerImageName.parse("oracleinanutshell/oracle-xe-11g")
            .asCompatibleSubstituteFor("gvenzl/oracle-xe");

    @Container
    public static OracleContainer oracle = new OracleContainer(ORACLE_IMAGE)
            .withInitScript("schema.sql"); // Assuming you have a schema.sql
    @PersistenceContext
    private EntityManager entityManager;

    @DynamicPropertySource
    static void oracleProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", oracle::getJdbcUrl);
        registry.add("spring.datasource.username", oracle::getUsername);
        registry.add("spring.datasource.password", oracle::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "none");
        registry.add("spring.jpa.properties.hibernate.dialect", () -> "org.hibernate.dialect.Oracle10gDialect");
    }

    @Test
    public void testGetStudentsByDepartment() {
        // Assuming the stored procedure is already created by the init script or manually

        oracle.waitingFor(Wait.forListeningPort());
        oracle.waitingFor(Wait.forLogMessage(".*Database ready.*", 1));


        // Create a StoredProcedureQuery instance
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetStudentsByDepartment", Student.class);
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Class.class, ParameterMode.REF_CURSOR);
        query.setParameter(1, 1); // Assuming '1' is a valid department ID

        // Execute and get results
        query.execute();
        List<Student> results = query.getResultList();

        // Assertions to verify the results
        assertFalse(results.isEmpty(), "The result should not be empty");
        // Additional assertions can be made based on expected results
    }
}
