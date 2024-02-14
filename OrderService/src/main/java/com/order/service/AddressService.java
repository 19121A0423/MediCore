package com.order.service;

import java.util.List;

import com.order.bean.Address;
import com.order.entity.AddressEntity;
import com.order.exceptions.AddressNotFoundException;

public interface AddressService {
	
	Address saveAddress(Address address);

	Address getAddressById(int id) throws AddressNotFoundException;

	List<Address> getAllAddresses() throws AddressNotFoundException;

	void deactivateAddress(int id) throws AddressNotFoundException;

	void updateAddress(int id, Address updatedAddress) throws AddressNotFoundException;

	void beanToEntity(Address address, AddressEntity addressEntity);

	void entityToBean(Address address, AddressEntity addressEntity);

	void entitiesToBeans(List<Address> addresses, List<AddressEntity> addressEntities);
	
	List<Address> getAddressByUserId(int userId) throws AddressNotFoundException;

}
