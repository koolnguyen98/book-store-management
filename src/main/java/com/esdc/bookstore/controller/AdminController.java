package com.esdc.bookstore.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esdc.bookstore.controller.form.AdditionalForm;
import com.esdc.bookstore.controller.form.BookForm;
import com.esdc.bookstore.controller.form.ProductTypeForm;
import com.esdc.bookstore.controller.form.StationeryForm;
import com.esdc.bookstore.entity.Author;
import com.esdc.bookstore.entity.Book;
import com.esdc.bookstore.entity.Brand;
import com.esdc.bookstore.entity.Order;
import com.esdc.bookstore.entity.Product;
import com.esdc.bookstore.entity.ProductType;
import com.esdc.bookstore.entity.PublishingCompany;
import com.esdc.bookstore.entity.ShoppingCart;
import com.esdc.bookstore.entity.Stationery;
import com.esdc.bookstore.service.NonScurityService;
import com.esdc.bookstore.service.ScurityService;

@Controller
public class AdminController {

	@Autowired
	private ScurityService scurityService;
	
	@Autowired
	private NonScurityService nonScurityService;

	/**
	 * 
	 * 
	 * Book Product 
	 * 
	 * 
	 **/

	@RequestMapping(value = "/admin/addBook", method = RequestMethod.POST)
	public String addBook(Model model, @ModelAttribute("bookForm") @Valid BookForm bookForm, Principal principal) {
		bookForm.setStatus(true);
		Book book = scurityService.insertBook(bookForm);
		
		BookForm bf = new BookForm();
		model.addAttribute("bookForm", bf);
		
		List<ProductType> productTypes = scurityService.findAllProductType();
		model.addAttribute("productTypes", productTypes);
		
		List<Author> authors = scurityService.findAllAuthor();
		model.addAttribute("authors", authors);
		
		List<PublishingCompany> publishingCompanies = scurityService.findAllPublishingCompany();
		model.addAttribute("publishingCompanies", publishingCompanies);

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			boolean admin = loginedUser.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ADMIN"));
			
			if (admin) {
				model.addAttribute("admin", true);
			}
				
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);
		
		List<Book> books = nonScurityService.findAllBook();
		model.addAttribute("books", books);

		return "redirect:/admin/book";
	}

	@RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
	public String updateBookPage(Model model, @PathVariable int id) {

		BookForm bookForm = scurityService.findBookById(id);

		if (bookForm != null) {
			model.addAttribute("bookForm", bookForm);
			
			List<ProductType> productTypes = scurityService.findAllProductType();
			model.addAttribute("productTypes", productTypes);
			
			List<Author> authors = scurityService.findAllAuthor();
			model.addAttribute("authors", authors);
			
			List<PublishingCompany> publishingCompanies = scurityService.findAllPublishingCompany();
			model.addAttribute("publishingCompanies", publishingCompanies);
			
			return "modalProduct";
		}
		
		return "redirect:/admin/book";
	}
	
	@RequestMapping(value = "/admin/editBook", method = RequestMethod.POST)
	public String updateBook(Model model, @ModelAttribute("bookForm") @Valid BookForm bookForm) {

		Book book = scurityService.updateBook(bookForm);

		return "redirect:/admin/book";
	}
	
	@RequestMapping(value = "/admin/deleteBook/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> deleteBook(Model model, @PathVariable int id) {

		Boolean deleteBook = scurityService.deleteBookById(id);
		
		return Collections.singletonMap("success", deleteBook);
	}
	
	@RequestMapping(value = "/admin/book", method = RequestMethod.GET)
	public String allBook(Model model, Principal principal) {

		BookForm bookForm = new BookForm();
		model.addAttribute("bookForm", bookForm);
		
		List<ProductType> productTypes = scurityService.findAllProductType();
		model.addAttribute("productTypes", productTypes);
		
		List<Author> authors = scurityService.findAllAuthor();
		model.addAttribute("authors", authors);
		
		List<PublishingCompany> publishingCompanies = scurityService.findAllPublishingCompany();
		model.addAttribute("publishingCompanies", publishingCompanies);

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			boolean admin = loginedUser.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ADMIN"));
			
			if (admin) {
				model.addAttribute("admin", true);
			}
				
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);
		
		List<Book> books = nonScurityService.findAllBook();
		model.addAttribute("books", books);
		
		return "manage-product";
	}
	
	/**
	 * 
	 *
	 *  Product type
	 * 
	 * 
	 **/
	
	@RequestMapping(value = "/admin/addProductType", method = RequestMethod.GET)
	public String addProductTypePage(Model model) {

		ProductTypeForm productTypeForm = new ProductTypeForm();
		model.addAttribute("productTypeForm", productTypeForm);

		return "addProductTypePage";
	}

	@RequestMapping(value = "/admin/addProductType", method = RequestMethod.POST)
	public String addProductType(Model model, @ModelAttribute("productTypeForm") @Valid ProductTypeForm productTypeForm) {

		ProductType productType = scurityService.insertProductType(productTypeForm);

		return "redirect:/home";
	}

	@RequestMapping(value = "/admin/updateProductType/{id}", method = RequestMethod.GET)
	public String updateProductTypePage(Model model, @PathVariable int id) {

		ProductTypeForm productTypeForm = scurityService.findProductTypeById(id);

		if (productTypeForm != null) {
			model.addAttribute("productTypeForm", productTypeForm);
			
			return "updateProductTypePage";
		}
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/admin/updateProductType", method = RequestMethod.POST)
	public String updateProductType(Model model, @ModelAttribute("productTypeForm") @Valid ProductTypeForm productTypeForm) {

		ProductType productType = scurityService.updateProductType(productTypeForm);

		return "redirect:/home";
	}
	
	@RequestMapping(value = "/admin/deleteProductType/{id}", method = RequestMethod.POST)
	public String deleteProductType(Model model, @PathVariable int id) {

		Boolean deleteProductType = scurityService.deleteProductTypeById(id);
		
		if(deleteProductType)
			return "true";
		else
			return "false";
	}
	
	/**
	 * 
	 *
	 *  Additional Properties
	 * 
	 * 
	 **/
	
	@RequestMapping(value = "/admin/additional", method = RequestMethod.GET)
	public String findAllAuthorAndPublishingCompany(Model model, Principal principal) {
		
		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			boolean admin = loginedUser.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ADMIN"));
			
			if (admin) {
				model.addAttribute("admin", true);
			}
				
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);

		List<Author> author = scurityService.findAllAuthor();
		model.addAttribute("authors", author);
		
		List<PublishingCompany> publishingCompany = scurityService.findAllPublishingCompany();
		model.addAttribute("publishingCompanys", publishingCompany);
		
		List<ProductType> productType = scurityService.findAllProductType();
		model.addAttribute("productTypes", productType);

		return "manage-author";
	}
	
	@RequestMapping(value = "/admin/addAdditionalProperties", method = RequestMethod.GET)
	public String addAdditionalPropertiesPage(Model model) {

		AdditionalForm additionalForm = new AdditionalForm();
		model.addAttribute("additionalForm", additionalForm);

		return "addProductTypePage";
	}

	@RequestMapping(value = "/admin/addAdditionalProperties", method = RequestMethod.POST)
	public String addAdditionalProperties(Model model, @ModelAttribute("additionalForm") @Valid AdditionalForm additionalForm) {

		Boolean result = scurityService.insertAdditionalProperties(additionalForm);

		return "redirect:/home";
	}

	@RequestMapping(value = "/admin/updateAdditional/{type}/{id}", method = RequestMethod.GET)
	public String updateAdditionalPage(Model model, @PathVariable("id") int id, @PathVariable("type") String type) {

		AdditionalForm additionalForm = scurityService.findAdditionalByIdAndType(id, type);

		if (additionalForm != null) {
			model.addAttribute("additionalForm", additionalForm);
			
			return "updateProductTypePage";
		}
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/admin/updateAdditional", method = RequestMethod.POST)
	public String updateAdditional(Model model, @ModelAttribute("additionalForm") @Valid AdditionalForm additionalForm) {

		Boolean result = scurityService.updateAdditional(additionalForm);

		return "redirect:/home";
	}
	
	@RequestMapping(value = "/admin/deleteAdditional/{id}", method = RequestMethod.POST)
	public String deleteAdditional(Model model, @PathVariable int id, @RequestParam(value="type") String type) {

		Boolean deleteAdditional = scurityService.deleteAdditionalByIdAndType(id, type);
		
		if(deleteAdditional)
			return "true";
		else
			return "false";
	}
	
	/**
	 * 
	 *
	 *  Bill manage
	 * 
	 * 
	 **/
	
	@RequestMapping(value = "/admin/bill", method = RequestMethod.GET)
	public String findAllBillByStatus(Model model, Principal principal) {

		List<ProductType> productTypes = nonScurityService.findAllProductType();

		model.addAttribute("productTypes", productTypes);

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			boolean admin = loginedUser.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ADMIN"));
			
			if (admin) {
				model.addAttribute("admin", true);
			}
				
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);
		
		List<Order> way = scurityService.findAllOrderByStatus("CXN");
		model.addAttribute("way", way);
		
		List<Order> confrim = scurityService.findAllOrderByStatus("DXN");
		model.addAttribute("confrim", confrim);
		
		List<Order> success = scurityService.findAllOrderByStatus("GTC");
		model.addAttribute("success", success);
		
		List<Order> unsuccess = scurityService.findAllOrderByStatus("GTB");
		model.addAttribute("unsuccess", unsuccess);

		return "manage-bill";
	}
	
	@RequestMapping(value = "/admin/bill/confirm/{id}", method = RequestMethod.POST)
	public String confirmOrder(Model model, Principal principal, @PathVariable int id) {

		List<ProductType> productTypes = nonScurityService.findAllProductType();

		model.addAttribute("productTypes", productTypes);

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			boolean admin = loginedUser.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ADMIN"));
			
			if (admin) {
				model.addAttribute("admin", true);
			}
				
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		
		boolean confirm = scurityService.confirmOrder(id);
		
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);
		
		List<Order> way = scurityService.findAllOrderByStatus("CXN");
		model.addAttribute("way", way);
		
		List<Order> confrim = scurityService.findAllOrderByStatus("DXN");
		model.addAttribute("confrim", confrim);
		
		List<Order> success = scurityService.findAllOrderByStatus("GTC");
		model.addAttribute("success", success);
		
		List<Order> unsuccess = scurityService.findAllOrderByStatus("GTB");
		model.addAttribute("unsuccess", unsuccess);
		
		return "manage-bill";
	}
	
	@RequestMapping(value = "/admin/bill/{id}", method = RequestMethod.GET)
	public String showOrder(Model model, Principal principal, @PathVariable int id) {

		List<ProductType> productTypes = nonScurityService.findAllProductType();

		model.addAttribute("productTypes", productTypes);

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			boolean admin = loginedUser.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ADMIN"));
			
			if (admin) {
				model.addAttribute("admin", true);
			}
				
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		
		Order order = scurityService.findOrderById(id);
		
		model.addAttribute("order", order);
		
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);
		
		List<Order> way = scurityService.findAllOrderByStatus("CXN");
		model.addAttribute("way", way);
		
		List<Order> confrim = scurityService.findAllOrderByStatus("DXN");
		model.addAttribute("confrim", confrim);
		
		List<Order> success = scurityService.findAllOrderByStatus("GTC");
		model.addAttribute("success", success);
		
		List<Order> unsuccess = scurityService.findAllOrderByStatus("GTB");
		model.addAttribute("unsuccess", unsuccess);
		
		return "modalBill";
	}
	
	@RequestMapping(value = "/admin/bill/dilivery/{id}", method = RequestMethod.POST)
	public String diliveryOrder(Model model, Principal principal, @PathVariable int id, @RequestParam("success") boolean status) {

		List<ProductType> productTypes = nonScurityService.findAllProductType();

		model.addAttribute("productTypes", productTypes);

		String userInfo = "";
		List<ShoppingCart> shoppingCarts = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = loginedUser.getUsername();
			
			boolean admin = loginedUser.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ADMIN"));
			
			if (admin) {
				model.addAttribute("admin", true);
			}
				
			shoppingCarts = nonScurityService.findAllShoppingCartByUser(userInfo);

			
		}
		
		boolean confirm = scurityService.confirmOrder(id, status);
		
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("userInfo", userInfo);
		
		List<Order> way = scurityService.findAllOrderByStatus("CXN");
		model.addAttribute("way", way);
		
		List<Order> confrim = scurityService.findAllOrderByStatus("DXN");
		model.addAttribute("confrim", confrim);
		
		List<Order> success = scurityService.findAllOrderByStatus("GTC");
		model.addAttribute("success", success);
		
		List<Order> unsuccess = scurityService.findAllOrderByStatus("GTB");
		model.addAttribute("unsuccess", unsuccess);
		
		return "manage-bill";
	}
}
