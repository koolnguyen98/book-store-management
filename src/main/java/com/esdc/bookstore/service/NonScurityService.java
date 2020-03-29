package com.esdc.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esdc.bookstore.entity.Book;
import com.esdc.bookstore.entity.Product;
import com.esdc.bookstore.entity.ProductType;
import com.esdc.bookstore.entity.Stationery;
import com.esdc.bookstore.repository.BookRepository;
import com.esdc.bookstore.repository.ProductRepository;
import com.esdc.bookstore.repository.ProductTypeRepository;
import com.esdc.bookstore.repository.StationeryRepository;

@Service
public class NonScurityService {
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private StationeryRepository stationeryRepository;

	public List<ProductType> findAllProductType() {
		return productTypeRepository.findAll();
	}

	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	public List<Book> findAllBook() {
		return bookRepository.findAll();
	}

	public List<Stationery> findAllStationery() {
		return stationeryRepository.findAll();
	}
}
