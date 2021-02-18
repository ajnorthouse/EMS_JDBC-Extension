package com.cognixia.jump.jdbc.ems_extension.team2.interfaces;

import java.util.List;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;

public interface EmployeeDAO {
	public List<Employee> getAllEmployees();
	public Employee getEmployee(int id);
	public int createEmployee(Employee em);
	public boolean deleteEmployee(Employee em);
	public boolean updateEmployee(Employee em);
}
