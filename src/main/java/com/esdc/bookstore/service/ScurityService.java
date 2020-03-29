package com.esdc.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esdc.bookstore.controller.form.BookForm;
import com.esdc.bookstore.controller.form.StationeryForm;
import com.esdc.bookstore.entity.Author;
import com.esdc.bookstore.entity.Book;
import com.esdc.bookstore.entity.Brand;
import com.esdc.bookstore.entity.ProductType;
import com.esdc.bookstore.entity.PublishingCompany;
import com.esdc.bookstore.entity.Stationery;
import com.esdc.bookstore.repository.AuthorRepository;
import com.esdc.bookstore.repository.BrandRepository;
import com.esdc.bookstore.repository.ProductTypeRepository;
import com.esdc.bookstore.repository.PublishingCompanyRepository;

@Service
public class ScurityService {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private PublishingCompanyRepository publishingCompanyRepository;
	
	@Autowired
	private BrandRepository brandRepository;

	public List<ProductType> findAllProductType() {
		return productTypeRepository.findAll();
	}

	public List<Author> findAllAuthor() {
		return authorRepository.findAll();
	}

	public List<PublishingCompany> findAllPublishingCompany() {
		return publishingCompanyRepository.findAll();
	}

	public List<Brand> findAllBrand() {
		return brandRepository.findAll();
	}
	
	/**
	 * 
	 *
	 * Product service
	 * 
	 * 
	 **/

	public BookForm findBookById(int id) {
		return productService.findBookById(id);
	}

	public Book insertBook(BookForm bookForm) {
		return productService.insertBook(bookForm);
	}

	public Book updateBook(BookForm bookForm) {
		return productService.updateBook(bookForm);
	}

	public Boolean deleteBookById(int id) {
		return productService.deleteBookById(id);
	}

	public Stationery insertStationery(StationeryForm stationeryForm) {
		return productService.insertStationery(stationeryForm);
	}

	public StationeryForm findStationeryById(int id) {
		return productService.findStationeryById(id);
	}

	public Stationery updateStationery(StationeryForm stationeryForm) {
		return productService.updateStationery(stationeryForm);
	}

	public Boolean deleteStationeryById(int id) {
		return productService.deleteStationeryById(id);
	}

	/**
	 * 
	 *
	 *  User Service
	 * 
	 * 
	 **/
	
	

}
