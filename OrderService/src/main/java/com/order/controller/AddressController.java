package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.Address;
import com.order.exceptions.AddressNotFoundException;
import com.order.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/save")
	public ResponseEntity<Address> saveAddress(@RequestBody Address address){
//		System.out.println("controller started");
		addressService.save(address);
//		System.out.println("controller ended");
		return new ResponseEntity<Address>(address, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") int id){
//		System.out.println("controller started");
		Address address = addressService.findById(id);
//		System.out.println("controller ended");
		return new ResponseEntity<Address>(address, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Address>> getAddress(){
//		System.out.println("controller started");
		List<Address> addresses = addressService.findAll();
//		System.out.println("controller ended");
		return new ResponseEntity<List<Address>>(addresses, HttpStatus.FOUND);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteAddressById(@PathVariable(value="id") int id) {
		try {
			addressService.delete(id);
			return "Successfully deleted address of id : "+id ;
		} catch (AddressNotFoundException e) {
//			e.printStackTrace();
			return e.getMessage();
		}
	}

}
