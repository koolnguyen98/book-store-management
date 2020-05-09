package com.esdc.bookstore.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.esdc.bookstore.controller.form.OrderForm;
import com.esdc.bookstore.entity.Order;
import com.esdc.bookstore.entity.OrderDetail;
import com.esdc.bookstore.entity.ShoppingCart;
import com.esdc.bookstore.entity.User;
import com.esdc.bookstore.service.NonScurityService;
import com.esdc.bookstore.service.OrderService;
import com.esdc.bookstore.service.ScurityService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ScurityService scurityService;

	@RequestMapping(value = "/shoppingcarts", method = RequestMethod.GET)
	public String findShoppingcartByUser(Model model, Principal principal) {

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			shoppingCarts = orderService.findShoppingcartByUsername(userInfo);

			
		}
		double totalPrice = 0.0;
		
		for (ShoppingCart shoppingCart : shoppingCarts) {
			totalPrice += shoppingCart.getTotalPrice();
		}
		
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);

		return "shopping-cart";
	}
	
	@RequestMapping(value = "/shoppingcarts/order", method = RequestMethod.GET)
	public String orderLink(Model model, Principal principal) {

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			shoppingCarts = orderService.findShoppingcartByUsername(userInfo);

			
		}
		double totalPrice = 0.0;
		
		for (ShoppingCart shoppingCart : shoppingCarts) {
			totalPrice += shoppingCart.getTotalPrice();
		}
		
		User user = scurityService.findUserByUserName(userInfo);
		
		model.addAttribute("user", user);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);

		return "check-out";
	}

	@RequestMapping(value = "/shoppingcarts/{id}", method = RequestMethod.POST)
	public String addProdcutIntoShoppingCart(Model model, @PathVariable("id") int id, @RequestParam(value="number") int number) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		boolean results = orderService.addProdcutIntoShoppingCart(id, userName, number);

		return "redirect:/home";
	}
	
	@RequestMapping(value = "/shoppingcarts/update/{id}", method = RequestMethod.POST)
	public String updateProdcutIntoShoppingCart(Model model, @PathVariable("id") int id, @RequestParam(value="number") int number) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		boolean results = orderService.updateAmountProdcutIntoShoppingCart(id, userName, number);

		return "redirect:/home";
	}

	@RequestMapping(value = "/shoppingcarts/{id}/addNumberProduct", method = RequestMethod.POST)
	public String addNumberProdcutIntoShoppingCart(Model model, @PathVariable int id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		boolean result = orderService.updateProdcutIntoShoppingCart(id, userName, 1);
		
		List<ShoppingCart> shoppingCarts = orderService.findShoppingcartByUsername(userName);

		if (result) {
			model.addAttribute("shoppingCarts", shoppingCarts);

		} else {
			model.addAttribute("message", "Can't add number product");
			model.addAttribute("shoppingCarts", shoppingCarts);
		}

		return "redirect:/shoppingcarts";
	}

	@RequestMapping(value = "/shoppingcarts/{id}/minusNumberProduct", method = RequestMethod.POST)
	public String minusNumberProdcutIntoShoppingCart(Model model, @PathVariable int id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		boolean result = orderService.updateProdcutIntoShoppingCart(id, userName, -1);
		
		List<ShoppingCart> shoppingCarts = orderService.findShoppingcartByUsername(userName);

		if (result) {
			model.addAttribute("shoppingCarts", shoppingCarts);

		} else {
			model.addAttribute("message", "Can't minus number product");
			model.addAttribute("shoppingCarts", shoppingCarts);
		}

		return "redirect:/shoppingcarts";
	}
	
	@RequestMapping(value = "/shoppingcarts/{id}/delete", method = RequestMethod.POST)
	public String deleteProdcutIntoShoppingCart(Model model, @PathVariable int id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		boolean result = orderService.deleteProductIntoShoppingCart(id, userName);
		
		List<ShoppingCart> shoppingCarts = orderService.findShoppingcartByUsername(userName);

		if (result) {
			model.addAttribute("shoppingCarts", shoppingCarts);

		} else {
			model.addAttribute("message", "Product doesn't exit into shopping cart");
			model.addAttribute("shoppingCarts", shoppingCarts);
		}

		return "redirect:/shoppingcarts";
	}
	
	@RequestMapping(value = "/shoppingcarts/ordering", method = RequestMethod.POST)
	public String ordering(Model model, @ModelAttribute("orderForm") @Valid OrderForm orderForm) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		
		List<ShoppingCart> shoppingCarts = orderService.findShoppingcartByUsername(userName);
		
		Order order = orderService.ordering(userName, orderForm);
		
		List<OrderDetail> orderDetails = orderService.findOrderDetailDByOrder(order);

		if (order != null) {
			model.addAttribute("order", order);
			model.addAttribute("orderDetails", orderDetails);
			return "orderViewPage";
		} else {
			model.addAttribute("message", "Order cannot be processed");
			model.addAttribute("shoppingCarts", shoppingCarts);
			return "redirect:/shoppingcarts";
		}
	}

}
