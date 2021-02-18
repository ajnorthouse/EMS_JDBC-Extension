package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.ConnectionManager;
import com.cognixia.jump.jdbc.ems_extension.team2.interfaces.CompanyDAO;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Company;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;

public class CompanyDAOClass implements CompanyDAO {
	
	Connection conn = ConnectionManager.getConnection();
	private String idMatchClause = " WHERE comp_id = ? ";
	private String companySelectClause = " SELECT c.*, a.address FROM company AS c INNER JOIN address AS a on c.address_id = a.address_id ";
	
	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		
		String allCompsQuery = companySelectClause;
		PreparedStatement allCompsStmt = conn.prepareStatement(allCompsQuery);
		
		List<Company> list = new ArrayList<Company>();
		ResultSet compResults = allCompsStmt.executeQuery();
		
		do {
			list.add(createCompanyObject(compResults));
		} while (compResults.next());
		
		return list;
	}

	
	@Override
	public Company getCompany(int id) {
		// TODO Auto-generated method stub
		String compQuery = companySelectClause + idMatchClause;
		PreparedStatement compStmt = conn.prepareStatement(compQuery);
		compStmt.setString(1, String.valueOf(id));
		
		ResultSet compResult = compStmt.executeQuery();
		return createCompanyObject(compResult);
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
	public List<Department> getCompanyDepartments(Company c) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private Company createCompanyObject(ResultSet rs) {
		int id = rs.getInt("comp_id");
		String name = rs.getString("comp_name");
		int budget = rs.getInt("comp_budget");
		String address = rs.getString("address");
		Company toReturn = new Company(name, budget, address);
		toReturn.setId(id);
		return toReturn;
	}
	
}




// A simple prepared statement with a null fail-safe.
public static ResultSet getDatabaseData(String[] userInput, Connection conn, PreparedStatement pstmt) {
	try {
		pstmt = conn.prepareStatement("SELECT first_name, last_name, credits FROM student WHERE (credits >= ? AND credits < ?) ORDER BY credits DESC;");
		pstmt.setString(1, userInput[0]);
		pstmt.setString(2, userInput[1]);
		return pstmt.executeQuery();
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}
