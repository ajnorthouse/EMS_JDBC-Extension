# address table
CREATE TABLE address
(
	address_id INT PRIMARY KEY UNIQUE AUTO_INCREMENT,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state CHAR(2) NOT NULL,
    zip_code VARCHAR(10)
); 
