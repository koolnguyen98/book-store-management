package com.esdc.bookstore.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esdc.bookstore.config.utils.WebUtils;
import com.esdc.bookstore.controller.form.BookForm;
import com.esdc.bookstore.controller.form.RegisterForm;
import com.esdc.bookstore.entity.Book;
import com.esdc.bookstore.entity.Product;
import com.esdc.bookstore.entity.ProductType;
import com.esdc.bookstore.entity.PublishingCompany;
import com.esdc.bookstore.entity.ShoppingCart;
import com.esdc.bookstore.entity.Stationery;
import com.esdc.bookstore.service.NonScurityService;

@Controller
public class BookStoreController {

	@Autowired
	private NonScurityService nonScurityService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(Model model, Principal principal) {

		List<ProductType> productTypes = nonScurityService.findAllProductType();

		model.addAttribute("productTypes", productTypes);

		List<Book> books = nonScurityService.findAllBook();

		model.addAttribute("books", books);

		List<Stationery> stationeries = nonScurityService.findAllStationery();

		model.addAttribute("stationeries", stationeries);

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);

		return "index";
	}

	@RequestMapping(value = { "/shop" }, method = RequestMethod.GET)
	public String shop(Model model, Principal principal) {

		List<ProductType> productTypes = nonScurityService.findAllProductType();

		model.addAttribute("productTypes", productTypes);

		List<PublishingCompany> publishingCompanies = nonScurityService.findAllPublishingCompany();

		model.addAttribute("publishingCompanies", publishingCompanies);

		List<Book> books = nonScurityService.findAllBook();

		model.addAttribute("books", books);

		List<Stationery> stationeries = nonScurityService.findAllStationery();

		model.addAttribute("stationeries", stationeries);

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);

		return "shop";
	}

	@RequestMapping(value = { "/shop/{acronym}" }, method = RequestMethod.GET)
	public String findByProductType(Model model, Principal principal, @PathVariable("acronym") String acronym) {

		List<ProductType> productTypes = nonScurityService.findAllProductType();

		model.addAttribute("productTypes", productTypes);

		List<PublishingCompany> publishingCompanies = nonScurityService.findAllPublishingCompany();

		model.addAttribute("publishingCompanies", publishingCompanies);

		List<Book> books = nonScurityService.findBookByProductAcronym(acronym);

		model.addAttribute("books", books);

		List<Stationery> stationeries = nonScurityService.findAllStationery();

		model.addAttribute("stationeries", stationeries);

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);

		return "shop";
	}

	@RequestMapping(value = {"/shop/{acronym}/product/{id}", "/shop/product/{id}"}, method = RequestMethod.GET)
	public String findByProdcut(Model model, Principal principal, @PathVariable("acronym") Optional<String> acronym,
			@PathVariable("id") int id) {

		List<ProductType> productTypes = nonScurityService.findAllProductType();

		model.addAttribute("productTypes", productTypes);

		List<PublishingCompany> publishingCompanies = nonScurityService.findAllPublishingCompany();

		model.addAttribute("publishingCompanies", publishingCompanies);

		Book book = nonScurityService.findBookByProductId(id);

		List<Book> books = null;
		
		model.addAttribute("book", book);
		
		if (acronym.isPresent()) {
			books = nonScurityService.findBookByProductAcronym(acronym.get());
		} else {
			books = nonScurityService.findAllBook();
		}
		
		model.addAttribute("books", books);

		List<Stationery> stationeries = nonScurityService.findAllStationery();

		model.addAttribute("stationeries", stationeries);

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);

		return "product";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model, Principal principal) {
		
		String userInfo = "";
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
		}

		model.addAttribute("userInfo", userInfo);
		
		if (userInfo != "") {
			return "redirect:/";
		}
		
		return "login";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		List<ProductType> productTypes = nonScurityService.findAllProductType();

		model.addAttribute("productTypes", productTypes);

		List<Book> books = nonScurityService.findAllBook();

		model.addAttribute("books", books);

		List<Stationery> stationeries = nonScurityService.findAllStationery();

		model.addAttribute("stationeries", stationeries);

		String userInfo = "";

		model.addAttribute("userInfo", userInfo);
		
		return "redirect:/";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model, Principal principal) {

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		return "adminPage";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();

		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		return "userInfoPage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "403Page";
	}
	
	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public String registerPage(Model model) {
		
		RegisterForm registerForm = new RegisterForm();
		
		model.addAttribute("registerForm", registerForm);
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model, @ModelAttribute("registerForm") @Validated RegisterForm registerForm) {
		
		return "register";
	}

}
