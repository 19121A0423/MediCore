package com.order.service;

import java.util.List;

import com.order.bean.Address;
import com.order.exceptions.AddressNotFoundException;

public interface AddressService {
	
	void save(Address address);

	Address findById(int id);

	List<Address> findAll();

	void delete(int id) throws AddressNotFoundException;


}
