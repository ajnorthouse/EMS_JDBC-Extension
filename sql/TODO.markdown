#Tables:

Address
- [x] address_id   int auto_increment primary
- [x] address      varchar(255)

Company
- [x] company_id   int auto_increment primary
- [x] company_name varchar(50)
- [x] budget       int
- [x] address_id   int foreign

Department
- [x] department_id int auto_increment primary
- [x] company_id    int foreign
- [x] name          varchar(50)
- [x] phone_number  varchar(11)
- [x] budget        int

Employee
- [x] employee_id	int auto_increment primary
- [x] deparment_id int foreign
- [x] first_name   varchar(15)
- [x] last_name		varchar(15)
- [x] salary       int
- [x] job_title    varchar(50)
- [x] phone_number varchar(11)
