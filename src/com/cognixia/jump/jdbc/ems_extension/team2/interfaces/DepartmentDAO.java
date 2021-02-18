package com.cognixia.jump.jdbc.ems_extension.team2.interfaces;

import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;

public interface DepartmentDAO {
	public List<Department> getAllDepartments();
	public Department getDepartment(int id);
	public boolean createDepartment(Department d);
	public boolean deleteDepartment(Department d);
	public boolean updateDepartment(Department d);
	public List<Employee> getDepartmentEmployees(Department d);
}
