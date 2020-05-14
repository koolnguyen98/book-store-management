package com.esdc.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.esdc.bookstore.controller.form.AdditionalForm;
import com.esdc.bookstore.controller.form.BookForm;
import com.esdc.bookstore.controller.form.ProductTypeForm;
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
	public String addBook(Model model, @ModelAttribute("bookForm") @Valid BookForm bookForm) {

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
	public String updateBook(Model model, @ModelAttribute("bookForm") @Valid BookForm bookForm) {

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
	public String findAllAuthorAndPublishingCompany(Model model) {

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
	
	
}
