#Department table
CREATE table department
(
	dept_id INT PRIMARY KEY UNIQUE AUTO_INCREMENT,
	dept_name VARCHAR(50) NOT NULL,
    dept_phone_num VARCHAR(14) NOT NULL,
	dept_budget INT NOT NULL,
    comp_id INT NOT NULL,
    FOREIGN KEY (comp_id) references company(comp_id)
    ON DELETE CASCADE
);