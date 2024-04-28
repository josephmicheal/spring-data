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



CREATE OR REPLACE PACKAGE student_pkg AS
    -- Declare the procedure in the package specification
    PROCEDURE GetStudentsByDepartment(p_department_id IN department.id%TYPE);
END student_pkg;



CREATE OR REPLACE PACKAGE BODY student_pkg AS

    -- Implementation of the GetStudentsByDepartment procedure
    PROCEDURE GetStudentsByDepartment(p_department_id IN department.id%TYPE)
    IS
        -- Cursor to hold the list of students for the given department
        CURSOR student_cursor IS
            SELECT id, name, address, course
            FROM student
            WHERE department_id = p_department_id;

        -- Record to fetch data into
        student_record student_cursor%ROWTYPE;
    BEGIN
        -- Open the cursor
        OPEN student_cursor;

        -- Fetch each row from the cursor and output it
        LOOP
            FETCH student_cursor INTO student_record;
            EXIT WHEN student_cursor%NOTFOUND;

            -- Displaying each student's details - this could be modified to suit how you want to output
            DBMS_OUTPUT.PUT_LINE('Student ID: ' || student_record.id || ', Name: ' || student_record.name ||
                                 ', Address: ' || student_record.address || ', Course: ' || student_record.course);
        END LOOP;

        -- Close the cursor
        CLOSE student_cursor;
    EXCEPTION
        WHEN OTHERS THEN
            -- If an error occurs, output the error and make sure cursor is closed
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
            IF student_cursor%ISOPEN THEN
                CLOSE student_cursor;
            END IF;
    END GetStudentsByDepartment;

END student_pkg;
