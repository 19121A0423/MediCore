package com.order.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.order.bean.AddressBean;
import com.order.controller.AddressController;
import com.order.exceptions.AddressNotFoundException;
import com.order.service.AddressService;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {
	
	@Mock
	private AddressService addressService;

	@InjectMocks
	private AddressController addressController;

	@Test
    void testSaveOrder() throws AddressNotFoundException {
        
        AddressBean address = new AddressBean();
        address.setAddressId(2);
        address.setStreetName("Sri Sailakshmi Boys Hostel, Anantapur Tirupati Chennai Highway, Chandragiri Subdistrict");
        address.setCity("Tirupati");
        address.setState("Andhra Pradesh");
        address.setPinCode(517102l);
        address.setUserId(1);
        address.setStatus("active");
       
        when(addressService.saveAddress(any(AddressBean.class))).thenReturn(address);
        
        ResponseEntity<AddressBean> response = addressController.saveAddress(address);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(address, response.getBody());

    }
	
	@Test
    void testUpdateAddress() throws AddressNotFoundException {
		
        AddressBean address = new AddressBean();
        address.setAddressId(1);
        address.setStreetName("BTM layout");
        address.setCity("Banglore");
        address.setState("Karnataka");
        address.setPinCode(617102L);
        address.setUserId(1);
        address.setStatus("active");

        int addressId = 1; 
        
        when(addressService.updateAddress(addressId, address)).thenReturn(address);
        ResponseEntity<AddressBean> responseEntity = addressController.updateAddress(address, addressId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(address, responseEntity.getBody());
    }
	
	@Test
    void testGetAddressById() throws AddressNotFoundException {
		
        int addressId = 1;
        
        AddressBean address = new AddressBean();
        address.setAddressId(addressId);
        address.setStreetName("BTM layout");
        address.setCity("Banglore");
        address.setState("Karnataka");
        address.setPinCode(617102L);
        address.setUserId(1);
        address.setStatus("active");
        
        when(addressService.getAddressById(addressId)).thenReturn(address);

        ResponseEntity<AddressBean> responseEntity = addressController.getAddressById(addressId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(address, responseEntity.getBody());
    }
	
	@Test
    void testGetAddressByUserId() throws AddressNotFoundException {
        int userId = 1; 

        List<AddressBean> addresses = new ArrayList<AddressBean>();
        AddressBean address = new AddressBean();
        address.setAddressId(2);
        address.setStreetName("BTM layout");
        address.setCity("Banglore");
        address.setState("Karnataka");
        address.setPinCode(617102L);
        address.setUserId(1);
        address.setStatus("active");
        addresses.add(address);
        
        when(addressService.getAddressByUserId(userId)).thenReturn(addresses);

        ResponseEntity<List<AddressBean>> responseEntity = addressController.getAddressByUserId(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(addresses, responseEntity.getBody());
    }

}
