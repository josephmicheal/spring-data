package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2Procedures {
    public static ResultSet getStudentsByDepartment(Connection conn, Integer departmentId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM STUDENT WHERE DEPARTMENT_ID = ?");
        statement.setInt(1, departmentId);
        return statement.executeQuery();
    }
}
