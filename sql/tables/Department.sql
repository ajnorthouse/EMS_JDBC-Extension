CREATE table department
(
	dept_id INT PRIMARY KEY UNIQUE AUTO_INCREMENT,
    dept_budget INT NOT NULL,
    dept_name VARCHAR(50) NOT NULL,
    dept_manager_name VARCHAR(50) NOT NULL,
    dept_manager_num VARCHAR(20),
    dept_manager_email VARCHAR(50) NOT NULL,
    comp_id INT NOT NULL,
    FOREIGN KEY (comp_id) references company(comp_id)
);

#Blaziken is the best pokemon
