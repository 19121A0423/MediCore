package com.admin.exception;

public class UserIdNotFoundException extends Exception{

		
		private String message;

		public UserIdNotFoundException(String message) {
			super(message);
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
		
		

	
}
