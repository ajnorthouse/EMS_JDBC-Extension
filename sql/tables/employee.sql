CREATE TABLE employee
(
	emp_id INT PRIMARY KEY UNIQUE AUTO_INCREMENT,
    first_name VARCHAR(15) NOT NULL,
    last_name VARCHAR(15) NOT NULL,
    salary INT NOT NULL,
    job_title VARCHAR(50) NOT NULL,
    phone_num VARCHAR(11),
    dept_id INT NOT NULL,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);
