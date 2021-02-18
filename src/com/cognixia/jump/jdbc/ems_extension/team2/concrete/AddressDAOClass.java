package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.Connection;
import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.ConnectionManager;
import com.cognixia.jump.jdbc.ems_extension.team2.interfaces.AddressDAO;

public class AddressDAOClass implements AddressDAO{

	Connection conn = ConnectionManager.getConnection();
	private String idMatchClause = " WHERE address_id = ? ";
	
	@Override
	public List<String> getAllAddresses() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String getAddress(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createAddress(String a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAddress(String a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAddress(String a) {
		// TODO Auto-generated method stub
		return false;
	}

}
