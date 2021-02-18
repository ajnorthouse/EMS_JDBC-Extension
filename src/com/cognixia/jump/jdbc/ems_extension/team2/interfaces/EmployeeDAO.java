package com.cognixia.jump.jdbc.ems_extension.team2.interfaces;

import java.util.List;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;

public interface EmployeeDAO {
	public List<Employee> getAllEmployees();
	public Employee getEmployee(int id);
	public boolean createEmployee(Employee e);
	public boolean deleteEmployee(Employee e);
	public boolean updateEmployee(Employee e);
}
