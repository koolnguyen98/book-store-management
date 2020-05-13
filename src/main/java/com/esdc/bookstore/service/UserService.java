package com.esdc.bookstore.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.esdc.bookstore.controller.form.RegisterForm;
import com.esdc.bookstore.entity.Account;
import com.esdc.bookstore.entity.User;
import com.esdc.bookstore.entity.User;
import com.esdc.bookstore.entity.Role;
import com.esdc.bookstore.repository.AccountRepository;
import com.esdc.bookstore.repository.RoleRepository;
import com.esdc.bookstore.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	public String createUser(RedirectAttributes redirect, Model model, RegisterForm registerForm) {
		String email = registerForm.getEmail();
		String username = registerForm.getUsername();

		User existedUser = userRepository.findByEmail(email);

		if (existedUser != null) {
			model.addAttribute("errorMessage", "Email has already taken");

			return "register";
		}

		Account existedAccount = accountRepository.findByUserName(username);

		if (existedAccount != null) {
			model.addAttribute("errorMessage", "Username has already taken");

			return "register";
		}

		User user = new User();

		user.setAddress(registerForm.getAddress());
		user.setEmail(registerForm.getEmail());
		user.setFullName(registerForm.getFullName());
		user.setPhoneNumber(registerForm.getPhoneNumber());
		user.setSex(registerForm.isSex());

		User entity = userRepository.save(user);

		if (entity == null) {
			model.addAttribute("errorMessage", "Create user failed");

			return "register";
		}

		Role userRole = roleRepository.findByRole("USER");

		List<Role> roles = new ArrayList<Role>();
		roles.add(userRole != null ? userRole : null);

		Account account = new Account();

		account.setUserName(registerForm.getUsername());
		account.setPassword(authService.hashpwd(registerForm.getPassword()));
		account.setUser(entity);
		account.setRoles(roles);

		Account addedAccount = accountRepository.save(account);

		if (addedAccount == null) {
			model.addAttribute("errorMessage", "Create account failed");

			return "register";
		}

		redirect.addFlashAttribute("successMessage", "Create account successfully");

		return "redirect:/registerPage";
	}

	public String userProfile(Model model, String username, RedirectAttributes redirect) {
		Account account = accountRepository.findByUserName(username);

		if (account == null) {
			model.addAttribute("errorMessage", "Username is not found");

			return "account";
		}

		User user = userRepository.findByAccount(account);

		if (user == null) {
			model.addAttribute("errorMessage", "Username is not found");

			return "account";
		}
		
		System.out.println(user.isSex());

		RegisterForm registerForm = new RegisterForm(user.getId(), user.getFullName(), user.getEmail(),
				user.getPhoneNumber(), user.getAddress(), user.isSex());

		model.addAttribute("registerForm", registerForm);

		return "account";
	}
	
	public String updateUserProfile(RedirectAttributes redirect, Model model, RegisterForm registerForm) {
		String email = registerForm.getEmail();

		User existedUser = userRepository.findByEmail(email);

		if (existedUser != null && existedUser.getId() != registerForm.getId()) {
			model.addAttribute("errorMessage", "Email has already taken");
			model.addAttribute("registerForm", registerForm);

			return "account";
		}


		User user = new User();

		user.setId(registerForm.getId());
		user.setAddress(registerForm.getAddress());
		user.setEmail(registerForm.getEmail());
		user.setFullName(registerForm.getFullName());
		user.setPhoneNumber(registerForm.getPhoneNumber());
		user.setSex(registerForm.isSex());

		User entity = userRepository.save(user);

		if (entity == null) {
			model.addAttribute("errorMessage", "Update user profile failed");
			model.addAttribute("registerForm", registerForm);
			
			return "account";
		}
		
		redirect.addFlashAttribute("successMessage", "Update account successfully");
		
		return "redirect:/user/profile";
	}
}
