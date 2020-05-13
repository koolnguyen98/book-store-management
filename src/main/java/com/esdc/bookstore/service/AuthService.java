package com.esdc.bookstore.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	public String hashpwd(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public boolean checkpwd(String plainPwd, String hashedPwd) {
		return BCrypt.checkpw(plainPwd, hashedPwd);
	}
}
