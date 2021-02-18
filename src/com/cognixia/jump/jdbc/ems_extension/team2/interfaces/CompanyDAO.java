package com.cognixia.jump.jdbc.ems_extension.team2.interfaces;

import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.model.Company;

public interface CompanyDAO {
	public List<Company> getAllCompanies();
	public Company getCompany(int id);
	public int createCompany(Company c);
	public boolean deleteCompany(Company c);
	public boolean updateCompany(Company c);
}
