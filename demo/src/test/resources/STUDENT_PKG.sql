create or replace NONEDITIONABLE PACKAGE student_pkg AS
    -- Define RefCursor type for returning cursors from procedures
    TYPE RefCursor IS REF CURSOR;

    -- Procedure to get students by department
    PROCEDURE GetStudentsByDepartment(
        p_department_id IN NUMBER,
        p_recordset OUT RefCursor);

    -- Procedure to get all student details along with their department names
    PROCEDURE GetStudentDetails(p_recordset OUT RefCursor);

    -- Declaration for inserting student details
    PROCEDURE InsertStudentDetails(
        p_name IN VARCHAR2,
        p_address IN VARCHAR2,
        p_course IN VARCHAR2,
        p_department_id IN NUMBER,
        p_student_id OUT NUMBER
    );
END student_pkg;
