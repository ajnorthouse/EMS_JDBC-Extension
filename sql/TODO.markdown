#Tables:

Address
- [] address_id   int auto_increment primary
- [] address      varchar(255)

Company
- [] company_id   int auto_increment primary
- [] company_name varchar(50)
- [] budget       int
- [] address_id   int foreign

Department
- [] department_id int auto_increment primary
- [] company_id    int foreign
- [] name          varchar(50)
- [] phone_number  varchar(11)
- [] budget        int

Employee
- [] employee_id	int auto_increment primary
- [] deparment_id int foreign
- [] first_name   varchar(15)
- [] last_name		varchar(15)
- [] salary       int
- [] job_title    varchar(50)
- [] phone_number varchar(11)
