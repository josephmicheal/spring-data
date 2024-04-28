package com.example.demo;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDepartment {
    private int studentId;
    private String studentName;
    private String departmentName;
}
