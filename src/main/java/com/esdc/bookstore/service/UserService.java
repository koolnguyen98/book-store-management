package com.esdc.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esdc.bookstore.controller.form.RegisterForm;
import com.esdc.bookstore.entity.Account;
import com.esdc.bookstore.entity.User;
import com.esdc.bookstore.entity.Role;
import com.esdc.bookstore.repository.AccountRepository;
import com.esdc.bookstore.repository.RoleRepository;
import com.esdc.bookstore.repository.UserRepository;
import org.springframework.ui.Model;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AuthService authService;
	
	public Model createUser(Model model, RegisterForm registerForm) {
		String email = registerForm.getEmail();
		String username = registerForm.getUsername();
		
		User existedUser = userRepository.findByEmail(email);
		
		if (existedUser != null) {
			model.addAttribute("errorMessage", "Email has already taken");
			
			return model;
		}
		
		Account existedAccount = accountRepository.findByUserName(username);
		
		if (existedAccount != null) {
			model.addAttribute("errorMessage", "Username has already taken");
			
			return model;
		}
		
		User user = new User();
		
		user.setAddress(registerForm.getAddress());
		user.setEmail(registerForm.getEmail());
		user.setFullName(registerForm.getFullName());
		user.setPhoneNumber(registerForm.getPhoneNumber());
		user.setSex(registerForm.isSex());
		
		User entity = userRepository.save(user);
		
		if (entity == null) {
			model.addAttribute("errorMessage", "Create account failed");
			
			return model;
		}
		
		Role userRole = roleRepository.findByRole("USER");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(userRole != null ? userRole : null);
		
		Account account = new Account();
		
		account.setUserName(registerForm.getUsername());
		account.setPassword(authService.hashpwd(registerForm.getPassword()));
		account.setUser(entity);
		account.setRoles(roles);
		
		accountRepository.save(account);
		
		model.addAttribute("message", "Create account successfully");
		
		return model;
	}
}
