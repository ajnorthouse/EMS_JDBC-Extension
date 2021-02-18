package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.Connection;
import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.ConnectionManager;
import com.cognixia.jump.jdbc.ems_extension.team2.interfaces.CompanyDAO;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Company;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;

public class CompanyDAOClass implements CompanyDAO {
	
	Connection conn = ConnectionManager.getConnection();
	
	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company getCompany(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createCompany(Company c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCompany(Company c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCompany(Company c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Department> getCompanyDepartments(Company d) {
		// TODO Auto-generated method stub
		return null;
	}

}
