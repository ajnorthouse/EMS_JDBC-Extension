package com.cognixia.jump.jdbc.ems_extension.team2.interfaces;

import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.model.Company;

public interface AddressDAO {
	public List<String> getAllAddresses();
	public String getAddress(int id);
	public boolean createAddress(String a);
	public boolean deleteAddress(String a);
	public boolean updateAddress(String a);
}
