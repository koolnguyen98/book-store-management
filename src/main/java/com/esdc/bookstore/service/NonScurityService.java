package com.esdc.bookstore.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esdc.bookstore.entity.Account;
import com.esdc.bookstore.entity.Book;
import com.esdc.bookstore.entity.Image;
import com.esdc.bookstore.entity.Product;
import com.esdc.bookstore.entity.ProductType;
import com.esdc.bookstore.entity.PublishingCompany;
import com.esdc.bookstore.entity.ShoppingCart;
import com.esdc.bookstore.entity.Stationery;
import com.esdc.bookstore.entity.User;
import com.esdc.bookstore.repository.AccountRepository;
import com.esdc.bookstore.repository.BookRepository;
import com.esdc.bookstore.repository.ProductRepository;
import com.esdc.bookstore.repository.ProductTypeRepository;
import com.esdc.bookstore.repository.PublishingCompanyRepository;
import com.esdc.bookstore.repository.ShoppingCartRepository;
import com.esdc.bookstore.repository.StationeryRepository;
import com.esdc.bookstore.repository.UserRepository;

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
	
	@Autowired
	private PublishingCompanyRepository publishingCompanyRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	public List<ProductType> findAllProductType() {
		return productTypeRepository.findAll();
	}

	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	public List<Book> findAllBook() {
		List<Book> books = bookRepository.findByStatus(true);
		
		return books;
	}
	
	public List<Book> findBookByProductAcronym(String acronym) {
		
		ProductType productType = productTypeRepository.findByAcronym(acronym);
		
		if(productType != null) {
			return bookRepository.findByProductType(productType);
		} else {
			return null;
		}
	}

	public List<Stationery> findAllStationery() {
		return stationeryRepository.findAll();
	}

	public List<PublishingCompany> findAllPublishingCompany() {
		return publishingCompanyRepository.findAll();
	}

	public Book findBookByProductId(int id) {
		
		Optional<Book> bOptional = bookRepository.findById(id);
		
		return bOptional.isPresent() ? bOptional.get() : null;
	}

	public List<ShoppingCart> findAllShoppingCartByUser(String userInfo) {
		
		Account account = accountRepository.findByUserName(userInfo);
		
		return shoppingCartRepository.findByShoppingCartKeyAccountId(account.getId());
	}

	public List<Book> findBookByName(String textSearch) {
		List<Book> books = bookRepository.findBookByName(textSearch);
		return books;
	}

	
}
