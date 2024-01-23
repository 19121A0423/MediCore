package com.ooms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ooms.entity.bean.CartBean;
import com.ooms.entity.bean.UserBean;
import com.ooms.util.ResponseStructure;

public interface UserService {
	
	
	public ResponseEntity<ResponseStructure<UserBean>> save(UserBean user);
	public ResponseEntity<ResponseStructure<UserBean>> update(UserBean user);
	public ResponseEntity<ResponseStructure<UserBean>> delete(int userId);
	public ResponseEntity<ResponseStructure<UserBean>> getById(int userId);
	public ResponseEntity<ResponseStructure<List<UserBean>>> getAll();
	public ResponseEntity<ResponseStructure<String>> userLogin(UserBean user);
	
	
	
//	public ResponseEntity<ResponseStructure<CartBean>> save(CartBean cart);
//	public ResponseEntity<ResponseStructure<CartBean>> update(CartBean cart);
//	public ResponseEntity<ResponseStructure<CartBean>> deleteCart(int cartId);
//	public ResponseEntity<ResponseStructure<CartBean>> getCartById(int cartId);
//	public ResponseEntity<ResponseStructure<List<CartBean>>> getAllProcuctsFromCartId(int cartId);
//	
	

}
