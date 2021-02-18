package com.cognixia.jump.jdbc.ems_extension.team2.interfaces;

import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;

public interface DepartmentDAO {
	public List<Department> getAllDepartments();
	public Department getDepartment(int id);
	public int createDepartment(Department d);
	public boolean deleteDepartment(Department d);
	public boolean updateDepartment(Department d);
	public List<Department> getAllDepartmentsOfCompany(int departmentId);
	public List<Employee> getDepartmentEmployees(int departmentId);
}
