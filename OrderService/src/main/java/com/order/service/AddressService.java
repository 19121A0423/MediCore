package com.order.service;

import java.util.List;

import com.order.bean.AddressBean;
import com.order.exceptions.AddressNotFoundException;

public interface AddressService {
	
	AddressBean saveAddress(AddressBean address);

	AddressBean getAddressById(int id) throws AddressNotFoundException;

	AddressBean updateAddress(int id, AddressBean updatedAddress) throws AddressNotFoundException;

	List<AddressBean> getAddressByUserId(int userId) throws AddressNotFoundException;

}
