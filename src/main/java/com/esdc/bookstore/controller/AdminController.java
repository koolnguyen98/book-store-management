package com.esdc.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esdc.bookstore.controller.form.BookForm;
import com.esdc.bookstore.controller.form.StationeryForm;
import com.esdc.bookstore.entity.Author;
import com.esdc.bookstore.entity.Book;
import com.esdc.bookstore.entity.Brand;
import com.esdc.bookstore.entity.ProductType;
import com.esdc.bookstore.entity.PublishingCompany;
import com.esdc.bookstore.entity.Stationery;
import com.esdc.bookstore.service.ScurityService;

@Controller
public class AdminController {

	@Autowired
	private ScurityService scurityService;

	/**
	 * 
	 * 
	 * Book Product 
	 * 
	 * 
	 **/
	
	@RequestMapping(value = "/admin/addBook", method = RequestMethod.GET)
	public String addBookPage(Model model) {

		BookForm bookForm = new BookForm();
		model.addAttribute("bookForm", bookForm);
		
		List<ProductType> productTypes = scurityService.findAllProductType();
		model.addAttribute("productTypes", productTypes);
		
		List<Author> authors = scurityService.findAllAuthor();
		model.addAttribute("authors", authors);
		
		List<PublishingCompany> publishingCompanies = scurityService.findAllPublishingCompany();
		model.addAttribute("publishingCompanies", publishingCompanies);

		return "addBookPage";
	}

	@RequestMapping(value = "/admin/addBook", method = RequestMethod.POST)
	public String addBook(Model model, @ModelAttribute("bookForm") BookForm bookForm) {

		Book book = scurityService.insertBook(bookForm);

		return "redirect:/home";
	}

	@RequestMapping(value = "/admin/updateBook/{id}", method = RequestMethod.GET)
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
			
			return "updateBookPage";
		}
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/admin/updateBook", method = RequestMethod.POST)
	public String updateBook(Model model, @ModelAttribute("bookForm") BookForm bookForm) {

		Book book = scurityService.updateBook(bookForm);

		return "redirect:/home";
	}
	
	@RequestMapping(value = "/admin/deleteBook/{id}", method = RequestMethod.GET)
	public String deleteBook(Model model, @PathVariable int id) {

		Boolean deleteBook = scurityService.deleteBookById(id);
		
		return "redirect:/home";
	}
	
	/**
	 * 
	 * 
	 * Stationery Product 
	 * 
	 * 
	 **/
	
	@RequestMapping(value = "/admin/addStationery", method = RequestMethod.GET)
	public String addStationeryPage(Model model) {

		StationeryForm stationeryForm = new StationeryForm();
		model.addAttribute("stationeryForm", stationeryForm);
		
		List<ProductType> productTypes = scurityService.findAllProductType();
		model.addAttribute("productTypes", productTypes);
		
		List<Brand> brands = scurityService.findAllBrand();
		model.addAttribute("brands", brands);

		return "addStationeryPage";
	}

	@RequestMapping(value = "/admin/addStationery", method = RequestMethod.POST)
	public String addStationery(Model model, @ModelAttribute("stationeryForm") StationeryForm stationeryForm) {

		Stationery stationery = scurityService.insertStationery(stationeryForm);

		return "redirect:/home";
	}

	@RequestMapping(value = "/admin/updateStationery/{id}", method = RequestMethod.GET)
	public String updateStationeryPage(Model model, @PathVariable int id) {

		StationeryForm stationeryForm = scurityService.findStationeryById(id);

		if (stationeryForm != null) {
			model.addAttribute("stationeryForm", stationeryForm);
			
			List<ProductType> productTypes = scurityService.findAllProductType();
			model.addAttribute("productTypes", productTypes);
			
			List<Brand> brands = scurityService.findAllBrand();
			model.addAttribute("brands", brands);
			
			return "updateStationeryPage";
		}
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/admin/updateStationery", method = RequestMethod.POST)
	public String updateStationery(Model model, @ModelAttribute("stationeryForm") StationeryForm stationeryForm) {

		Stationery stationery = scurityService.updateStationery(stationeryForm);

		return "redirect:/home";
	}
	
	@RequestMapping(value = "/admin/deleteStationery/{id}", method = RequestMethod.GET)
	public String deleteStationery(Model model, @PathVariable int id) {

		Boolean deleteStationrery = scurityService.deleteStationeryById(id);
		
		return "redirect:/home";
	}
}
