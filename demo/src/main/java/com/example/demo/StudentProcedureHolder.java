package com.example.demo;

import jakarta.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Student.getStudentsByDepartment",
                procedureName = "student_pkg.GetStudentsByDepartment",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_department_id"),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class, name = "p_recordset")
                },
                resultClasses = Student.class
        ),
        @NamedStoredProcedureQuery(
                name = "GetStudentDetails",
                procedureName = "student_pkg.GetStudentDetails",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
                },
                resultSetMappings = "StudentDepartmentMapping"
        ),
        @NamedStoredProcedureQuery(
                name = "Student.insertStudentDetails",
                procedureName = "student_pkg.InsertStudentDetails",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_name"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_address"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_course"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_department_id"),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "p_student_id")
                }
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "StudentDepartmentMapping",
                classes = @ConstructorResult(
                        targetClass = StudentDepartment.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "department_name", type = String.class)
                        }
                )
        )
})
@Entity
public class StudentProcedureHolder {
    @Id
    private int id;
}
