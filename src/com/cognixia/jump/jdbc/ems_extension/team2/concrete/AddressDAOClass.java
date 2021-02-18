package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.Connection;
import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.ConnectionManager;
import com.cognixia.jump.jdbc.ems_extension.team2.interfaces.AddressDAO;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Address;

public class AddressDAOClass implements AddressDAO{

	Connection conn = ConnectionManager.getConnection();
	private String idMatchClause = " WHERE address_id = ? ";
	
	
	@Override
	public List<Address> getAllAddresses() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Address getAddress(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean createAddress(Address a) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteAddress(Address a) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateAddress(Address a) {
		// TODO Auto-generated method stub
		return false;
	}
	


}
