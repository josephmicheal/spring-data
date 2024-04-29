CREATE TABLE department (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    faculty VARCHAR(255) NOT NULL
);


CREATE TABLE student (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    course VARCHAR(100),
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES department(id)
);

INSERT INTO department (id, name, faculty) VALUES (1, 'Computer Science', 'Computing and Information Sciences');
INSERT INTO department (id, name, faculty) VALUES (2, 'Mechanical Engineering', 'Engineering and Technology');


INSERT INTO student (id, name, address, course, department_id) VALUES (1, 'Alice Johnson', '123 Maple St', 'Software Engineering', 1);
INSERT INTO student (id, name, address, course, department_id) VALUES (2, 'Bob Smith', '234 Oak Ave', 'Data Science', 1);
INSERT INTO student (id, name, address, course, department_id) VALUES (3, 'Carol White', '345 Pine Rd', 'Machine Learning', 1);
INSERT INTO student (id, name, address, course, department_id) VALUES (4, 'Dave Brown', '456 Birch Blvd', 'Thermal Engineering', 2);
INSERT INTO student (id, name, address, course, department_id) VALUES (5, 'Eve Davis', '567 Cedar Lane', 'Fluid Mechanics', 2);



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
