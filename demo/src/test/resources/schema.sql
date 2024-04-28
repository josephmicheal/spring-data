-- Create the DEPARTMENT table
CREATE TABLE department (
    id NUMBER(10) PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    faculty VARCHAR2(255) NOT NULL
);

-- Create the STUDENT table
CREATE TABLE student (
    id NUMBER(10) PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    address VARCHAR2(255),
    course VARCHAR2(100),
    department_id NUMBER(10),
    CONSTRAINT fk_department
        FOREIGN KEY (department_id)
        REFERENCES department (id)
);

-- Insert example data into DEPARTMENT table
INSERT INTO department (id, name, faculty) VALUES (1, 'Computer Science', 'Computing and Information Sciences');
INSERT INTO department (id, name, faculty) VALUES (2, 'Mechanical Engineering', 'Engineering and Technology');

-- Insert example data into STUDENT table
INSERT INTO student (id, name, address, course, department_id) VALUES (1, 'Alice Johnson', '123 Maple St', 'Software Engineering', 1);
INSERT INTO student (id, name, address, course, department_id) VALUES (2, 'Bob Smith', '234 Oak Ave', 'Data Science', 1);
INSERT INTO student (id, name, address, course, department_id) VALUES (3, 'Carol White', '345 Pine Rd', 'Machine Learning', 1);
INSERT INTO student (id, name, address, course, department_id) VALUES (4, 'Dave Brown', '456 Birch Blvd', 'Thermal Engineering', 2);
INSERT INTO student (id, name, address, course, department_id) VALUES (5, 'Eve Davis', '567 Cedar Lane', 'Fluid Mechanics', 2);
