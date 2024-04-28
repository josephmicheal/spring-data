create or replace NONEDITIONABLE PACKAGE BODY student_pkg AS

    -- Implementation for getting students by department
    PROCEDURE GetStudentsByDepartment(
        p_department_id IN NUMBER,
        p_recordset OUT RefCursor)
    IS
    BEGIN
        OPEN p_recordset FOR
        SELECT s.id AS "id",
               s.name AS "name",
               s.address AS "address",  -- Include this column
               s.course AS "course",
               s.department_id AS "department_id"  -- Ensure the alias matches the @Column annotation if necessary.
        FROM student s
            WHERE s.department_id = p_department_id;
    END GetStudentsByDepartment;

    -- Implementation for getting all student details with department names
    PROCEDURE GetStudentDetails(p_recordset OUT RefCursor)
    IS
    BEGIN
        OPEN p_recordset FOR
            SELECT s.id,s.name, d.name AS department_name
            FROM student s
            JOIN department d ON s.department_id = d.id;
    END GetStudentDetails;

-- Procedure to insert student details
PROCEDURE InsertStudentDetails(
        p_name IN VARCHAR2,
        p_address IN VARCHAR2,
        p_course IN VARCHAR2,
        p_department_id IN NUMBER,
        p_student_id OUT NUMBER
    ) AS
    BEGIN
        SELECT student_seq.NEXTVAL INTO p_student_id FROM dual;
        INSERT INTO student (id, name, address, course, department_id)
        VALUES (p_student_id, p_name, p_address, p_course, p_department_id);
    EXCEPTION
        WHEN OTHERS THEN
            RAISE;
    END InsertStudentDetails;


END student_pkg;
