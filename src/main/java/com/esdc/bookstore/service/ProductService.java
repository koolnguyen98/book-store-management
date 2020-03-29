package com.esdc.bookstore.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esdc.bookstore.controller.form.BookForm;
import com.esdc.bookstore.controller.form.StationeryForm;
import com.esdc.bookstore.entity.Book;
import com.esdc.bookstore.entity.OrderDetail;
import com.esdc.bookstore.entity.Product;
import com.esdc.bookstore.entity.ShoppingCart;
import com.esdc.bookstore.entity.Stationery;
import com.esdc.bookstore.repository.AuthorRepository;
import com.esdc.bookstore.repository.BookRepository;
import com.esdc.bookstore.repository.BrandRepository;
import com.esdc.bookstore.repository.OrderDetailRepository;
import com.esdc.bookstore.repository.ProductRepository;
import com.esdc.bookstore.repository.ProductTypeRepository;
import com.esdc.bookstore.repository.PublishingCompanyRepository;
import com.esdc.bookstore.repository.ShoppingCartRepository;
import com.esdc.bookstore.repository.StationeryRepository;

@Service
public class ProductService {
	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private PublishingCompanyRepository publishingCompanyRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private StationeryRepository stationeryRepository;

	public Book insertBook(BookForm bookForm) {
		Book book = new Book();

		book.setAmount(bookForm.getAmount());
		book.setAuthor(authorRepository.findById(bookForm.getAuthor()).get());
		book.setDateCreate(new Timestamp(System.currentTimeMillis()));
		book.setDescription(bookForm.getDescription());
		book.setDiscount(bookForm.getDiscount());
		book.setFormBookJacket(bookForm.getFormBookJacket());
		book.setLanguage(bookForm.getLanguage());
		book.setPageNumber(bookForm.getPageNumber());
		book.setPrice(bookForm.getPrice());
		book.setProductName(bookForm.getProductName());
		book.setProductType(productTypeRepository.findById(bookForm.getProductType()).get());
		book.setPublishingCompany(publishingCompanyRepository.findById(bookForm.getPublishingCompany()).get());
		book.setPublishingYear(bookForm.getPublishingYear());
		book.setSize(bookForm.getSize());
		book.setStatus(bookForm.getStatus());
		book.setTranslator(bookForm.getTranslator());

		return bookRepository.save(book);
	}

	public BookForm findBookById(int id) {
		Optional<Book> bookOpt = bookRepository.findById(id);
		Book book = bookOpt.isPresent() ? bookOpt.get() : null;
		if (book != null) {
			BookForm bookForm = new BookForm();
			bookForm.setId(book.getId());
			bookForm.setAmount(book.getAmount());
			bookForm.setAuthor(book.getAuthor().getId());
			bookForm.setDescription(book.getDescription());
			bookForm.setDiscount(book.getDiscount());
			bookForm.setFormBookJacket(book.getFormBookJacket());
			bookForm.setLanguage(book.getLanguage());
			bookForm.setPageNumber(book.getPageNumber());
			bookForm.setPrice(book.getPrice());
			bookForm.setProductName(book.getProductName());
			bookForm.setProductType(book.getProductType().getId());
			bookForm.setPublishingCompany(book.getPublishingCompany().getId());
			bookForm.setPublishingYear(book.getPublishingYear());
			bookForm.setSize(book.getSize());
			bookForm.setStatus(book.getStatus());
			bookForm.setTranslator(book.getTranslator());

			return bookForm;
		}
		return null;
	}

	public Book updateBook(BookForm bookForm) {
		Optional<Book> bookOpt = bookRepository.findById(bookForm.getId());
		Book book = bookOpt.isPresent() ? bookOpt.get() : null;
		if (book != null) {
			book.setAmount(bookForm.getAmount());
			book.setAuthor(authorRepository.findById(bookForm.getAuthor()).get());
			book.setDateCreate(new Timestamp(System.currentTimeMillis()));
			book.setDescription(bookForm.getDescription());
			book.setDiscount(bookForm.getDiscount());
			book.setFormBookJacket(bookForm.getFormBookJacket());
			book.setLanguage(bookForm.getLanguage());
			book.setPageNumber(bookForm.getPageNumber());
			book.setPrice(bookForm.getPrice());
			book.setProductName(bookForm.getProductName());
			book.setProductType(productTypeRepository.findById(bookForm.getProductType()).get());
			book.setPublishingCompany(publishingCompanyRepository.findById(bookForm.getPublishingCompany()).get());
			book.setPublishingYear(bookForm.getPublishingYear());
			book.setSize(bookForm.getSize());
			book.setStatus(bookForm.getStatus());
			book.setTranslator(bookForm.getTranslator());

			return bookRepository.save(book);
		}
		return null;
	}

	public Boolean deleteBookById(int id) {
		Optional<Book> bookOpt = bookRepository.findById(id);
		Book book = bookOpt.isPresent() ? bookOpt.get() : null;
		Optional<Product> productOtp = productRepository.findById(id);
		Product product = productOtp.isPresent() ? productOtp.get() : null;
		if (book != null && product != null) {
			List<ShoppingCart> spcbooks = shoppingCartRepository.findByProduct(product);
			List<OrderDetail> oddbooks = orderDetailRepository.findByProduct(product);
			
			if(spcbooks.isEmpty() && oddbooks.isEmpty()) {
				bookRepository.delete(book);
			}
			book.setStatus(false);
			bookRepository.save(book);
			return true;
		}
		return false;
	}

	public Stationery insertStationery(StationeryForm stationeryForm) {
		Stationery stationery = new Stationery();

		stationery.setAmount(stationeryForm.getAmount());
		stationery.setDateCreate(new Timestamp(System.currentTimeMillis()));
		stationery.setDescription(stationeryForm.getDescription());
		stationery.setDiscount(stationeryForm.getDiscount());
		stationery.setMadeIn(stationeryForm.getMadeIn());
		stationery.setParameter(stationeryForm.getParameter());
		stationery.setPrice(stationeryForm.getPrice());
		stationery.setProductName(stationeryForm.getProductName());
		stationery.setProductType(productTypeRepository.findById(stationeryForm.getProductType()).get());
		stationery.setBrand(brandRepository.findById(stationeryForm.getBrand()).get());
		stationery.setSize(stationeryForm.getSize());
		stationery.setStatus(stationeryForm.getStatus());

		return stationeryRepository.save(stationery);
	}

	public StationeryForm findStationeryById(int id) {
		Optional<Stationery> stationeryOpt = stationeryRepository.findById(id);
		Stationery stationery = stationeryOpt.isPresent() ? stationeryOpt.get() : null;
		if (stationery != null) {
			StationeryForm stationeryForm = new StationeryForm();
			stationeryForm.setId(stationery.getId());
			stationeryForm.setAmount(stationery.getAmount());
			stationeryForm.setDescription(stationery.getDescription());
			stationeryForm.setDiscount(stationery.getDiscount());
			stationeryForm.setMadeIn(stationery.getMadeIn());
			stationeryForm.setParameter(stationery.getParameter());
			stationeryForm.setPrice(stationery.getPrice());
			stationeryForm.setProductName(stationery.getProductName());
			stationeryForm.setProductType(stationery.getProductType().getId());
			stationeryForm.setBrand(stationery.getBrand().getId());
			stationeryForm.setSize(stationery.getSize());
			stationeryForm.setStatus(stationery.getStatus());

			return stationeryForm;
		}
		return null;
	}

	public Stationery updateStationery(StationeryForm stationeryForm) {
		Optional<Stationery> stationeryOpt = stationeryRepository.findById(stationeryForm.getId());
		Stationery stationery = stationeryOpt.isPresent() ? stationeryOpt.get() : null;
		if (stationery != null) {
			stationery.setAmount(stationeryForm.getAmount());
			stationery.setDateCreate(new Timestamp(System.currentTimeMillis()));
			stationery.setDescription(stationeryForm.getDescription());
			stationery.setDiscount(stationeryForm.getDiscount());
			stationery.setMadeIn(stationeryForm.getMadeIn());
			stationery.setParameter(stationeryForm.getParameter());
			stationery.setPrice(stationeryForm.getPrice());
			stationery.setProductName(stationeryForm.getProductName());
			stationery.setProductType(productTypeRepository.findById(stationeryForm.getProductType()).get());
			stationery.setBrand(brandRepository.findById(stationeryForm.getBrand()).get());
			stationery.setSize(stationeryForm.getSize());
			stationery.setStatus(stationeryForm.getStatus());

			return stationeryRepository.save(stationery);
		}
		return null;
	}

	public Boolean deleteStationeryById(int id) {
		Optional<Stationery> stationeryOpt = stationeryRepository.findById(id);
		Stationery stationery = stationeryOpt.isPresent() ? stationeryOpt.get() : null;
		Optional<Product> productOtp = productRepository.findById(id);
		Product product = productOtp.isPresent() ? productOtp.get() : null;
		if (stationery != null && product != null) {
			List<ShoppingCart> spcStationerys = shoppingCartRepository.findByProduct(product);
			List<OrderDetail> oddStationerys = orderDetailRepository.findByProduct(product);
			
			if(spcStationerys.isEmpty() && oddStationerys.isEmpty()) {
				stationeryRepository.delete(stationery);
			}
			stationery.setStatus(false);
			stationeryRepository.save(stationery);
			return true;
		}
		return false;
	}
}
