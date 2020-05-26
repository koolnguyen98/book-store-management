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
import com.esdc.bookstore.entity.Role;
import com.esdc.bookstore.repository.AccountRepository;
import com.esdc.bookstore.repository.RoleRepository;
import com.esdc.bookstore.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

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
		account.setEnabled(true);

		Account addedAccount = accountRepository.save(account);

		if (addedAccount == null) {
			model.addAttribute("errorMessage", "Create account failed");

			return "register";
		}

		redirect.addFlashAttribute("successMessage", "Create account successfully");

		return "redirect:/registerPage";
	}

	public String userProfile(Model model, String username, RedirectAttributes redirect) {
		try {
		Account account = accountRepository.findByUserName(username);

		if (account == null) {
			model.addAttribute("errorMessage", "Username is not found");
			System.out.println("account");
			return "redirect:/login";
		}
		
		System.out.println(account.toString());
		
		User user = userRepository.findUserByAccountId(account.getId());

		if (user == null) {
			model.addAttribute("errorMessage", "Username is not found");
			System.out.println("user");
			return "redirect:/login";
		}
		
		System.out.println(user.toString());
		

		RegisterForm registerForm = new RegisterForm((Long) user.getId(), user.getFullName(), user.getEmail(),
				user.getPhoneNumber(), user.getAddress(), user.isSex());
		
		System.out.println(registerForm.toString());

		model.addAttribute("registerForm", registerForm);
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

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
	
	public String blockUser(Model model, RedirectAttributes redirect, Integer accountID) {
Account account = accountRepository.findAccountById(accountID);
		
		if (account == null) {
			redirect.addFlashAttribute("messageError", "Account not found");
			
			return "redirect:/admin/users";
		}
		
		try {
			account.setEnabled(false);
			accountRepository.save(account);
		} catch(Exception e) {
			redirect.addFlashAttribute("messageError", "Block account failed");
			
			return "redirect:/admin/users";
		}
		
		redirect.addFlashAttribute("successMessage", "Block account successfully");
		return "redirect:/admin/users";
	}
	
	public String unblockUser(Model model, RedirectAttributes redirect, Integer accountID) {
		Account account = accountRepository.findAccountById(accountID);
		
		if (account == null) {
			redirect.addFlashAttribute("messageError", "Account not found");
			
			return "redirect:/admin/users";
		}
		
		try {
			account.setEnabled(true);
			accountRepository.save(account);
		} catch(Exception e) {
			redirect.addFlashAttribute("messageError", "Unblock account failed");
			
			return "redirect:/admin/users";
		}
		
		redirect.addFlashAttribute("successMessage", "Unblock account successfully");
		return "redirect:/admin/users";
	}
}
