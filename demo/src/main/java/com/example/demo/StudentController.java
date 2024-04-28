package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/add-student")
    public ResponseEntity<?> addStudent(@RequestBody StudentDTO studentDTO) {
        try {
            Integer studentId = studentService.addStudent(
                    studentDTO.getName(),
                    studentDTO.getAddress(),
                    studentDTO.getCourse(),
                    studentDTO.getDepartmentId()
            );
            return ResponseEntity.ok().body("Student added successfully with ID: " + studentId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add student: " + e.getMessage());
        }
    }

    @GetMapping("/details")
    public ResponseEntity<List<StudentDepartment>> getStudentDepartmentDetails() {
        List<StudentDepartment> studentDetails = studentService.getStudentDetails();
        if (studentDetails.isEmpty()) {
            return ResponseEntity.noContent().build();  // Return HTTP 204 No Content if the list is empty
        }
        return ResponseEntity.ok(studentDetails);  // Return HTTP 200 OK with the student details list
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Student>> getAllStudentsByDepartmentId(@PathVariable int departmentId) {
        List<Student> students = studentService.getStudentsByDepartment(departmentId);
        return ResponseEntity.ok(students);
    }

    // Endpoint to retrieve all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Endpoint to retrieve a single student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to create or update a student
    @PostMapping
    public ResponseEntity<Student> saveOrUpdateStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveOrUpdateStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    // Endpoint to update a student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        return studentService.getStudentById(id)
                .map(storedStudent -> {
                    student.setId(id); // Ensure the ID is not changed
                    Student updatedStudent = studentService.saveOrUpdateStudent(student);
                    return ResponseEntity.ok(updatedStudent);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        if (studentService.getStudentById(id).isPresent()) {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
