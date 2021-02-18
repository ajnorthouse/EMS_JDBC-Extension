# Company table
CREATE TABLE company
(
	comp_id INT PRIMARY KEY UNIQUE AUTO_INCREMENT,
    comp_name VARCHAR(50) NOT NULL,
    comp_budget INT NOT NULL
);