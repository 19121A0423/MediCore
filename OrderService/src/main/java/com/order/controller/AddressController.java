package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.Address;
import com.order.bean.UserBean;
import com.order.exceptions.AddressNotFoundException;
import com.order.exceptions.UserNotFoundException;
import com.order.service.AddressService;
import com.order.service.UserService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<Address> saveAddress(@RequestBody Address address){
		try {
			addressService.saveAddress(address);
			return new ResponseEntity<Address>(address, HttpStatus.CREATED);
		}
		catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable(value = "id") int id) {
	    try {
	        addressService.updateAddress(id, address);
	        return new ResponseEntity<>(address, HttpStatus.OK);
	    } catch (AddressNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") int id) {
	    try {
	        Address address = addressService.getAddressById(id);
	        return new ResponseEntity<>(address, HttpStatus.OK);
	    } catch (AddressNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<Address>> getAddress() {
	    List<Address> addresses;
		try {
			addresses = addressService.getAllAddresses();
			return new ResponseEntity<>(addresses, HttpStatus.OK);	
		} catch (AddressNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deactivateAddressById(@PathVariable(value="id") int id) {
		try {
	        addressService.deactivateAddress(id);
	        return new ResponseEntity<>("Successfully deleted order of id: " + id, HttpStatus.OK);
	    } catch (AddressNotFoundException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("getUser/{id}")
	public ResponseEntity<UserBean> getUser(@PathVariable(value="id") int id) {
	    UserBean user;
		try {
			user = userService.getUserBean(id);
			return new ResponseEntity<>(user, HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}	
	}

}
