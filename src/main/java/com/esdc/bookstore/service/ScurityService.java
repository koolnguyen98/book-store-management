package com.esdc.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esdc.bookstore.controller.form.AdditionalForm;
import com.esdc.bookstore.controller.form.BookForm;
import com.esdc.bookstore.controller.form.ProductTypeForm;
import com.esdc.bookstore.controller.form.StationeryForm;
import com.esdc.bookstore.entity.Account;
import com.esdc.bookstore.entity.Author;
import com.esdc.bookstore.entity.Book;
import com.esdc.bookstore.entity.Brand;
import com.esdc.bookstore.entity.Order;
import com.esdc.bookstore.entity.ProductType;
import com.esdc.bookstore.entity.PublishingCompany;
import com.esdc.bookstore.entity.Role;
import com.esdc.bookstore.entity.Stationery;
import com.esdc.bookstore.entity.Status;
import com.esdc.bookstore.entity.User;
import com.esdc.bookstore.repository.AccountRepository;
import com.esdc.bookstore.repository.AuthorRepository;
import com.esdc.bookstore.repository.BrandRepository;
import com.esdc.bookstore.repository.OrderRepository;
import com.esdc.bookstore.repository.ProductTypeRepository;
import com.esdc.bookstore.repository.PublishingCompanyRepository;
import com.esdc.bookstore.repository.RoleRepository;
import com.esdc.bookstore.repository.StatusRepository;
import com.esdc.bookstore.repository.UserRepository;

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

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private StatusRepository statusRepository;

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

	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}

	/**
	 * 
	 *
	 * Product service
	 * 
	 * Add, update, delete product (Book and Stationery), product type, brand,
	 * author and publishing company
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

	public ProductTypeForm findProductTypeById(int id) {
		return productService.findProductTypeById(id);
	}

	public ProductType insertProductType(ProductTypeForm productTypeForm) {
		return productService.insertProductType(productTypeForm);
	}

	public ProductType updateProductType(ProductTypeForm productTypeForm) {
		return productService.updateProductType(productTypeForm);
	}

	public Boolean deleteProductTypeById(int id) {
		return productService.deleteProductTypeById(id);
	}

	public Boolean insertAdditionalProperties(AdditionalForm additionalForm) {
		return productService.insertAdditionalProperties(additionalForm);
	}

	public AdditionalForm findAdditionalByIdAndType(int id, String type) {
		return productService.findAdditionalByIdAndType(id, type);
	}

	public Boolean updateAdditional(AdditionalForm additionalForm) {
		return productService.updateAdditional(additionalForm);
	}

	public Boolean deleteAdditionalByIdAndType(int id, String type) {
		return productService.deleteAdditionalByIdAndType(id, type);
	}

	public User findUserByUserName(String userName) {

		Account account = accountRepository.findByUserName(userName);

		return userRepository.findByAccount(account);
	}

	public List<Order> findAllOrderByStatus(String status) {
		Status st = null;

		if (status.equals("CXN")) {
			st = statusRepository.findByStatus("Cho Xac Nhan");
		} else if (status.equals("DXN")) {
			st = statusRepository.findByStatus("Da Xac Nhan");
		} else if (status.equals("GTC")) {
			st = statusRepository.findByStatus("Giao Hang Thanh Cong");
		} else if (status.equals("GTB")) {
			st = statusRepository.findByStatus("Giao Hang That Bai");
		}

		List<Order> orders = orderRepository.findByStatus(st);
		return orders;
	}

	public boolean confirmOrder(int id) {
		Optional<Order> orOptional = orderRepository.findById(id);

		Order order = orOptional.isPresent() ? orOptional.get() : null;

		if (order == null) {
			return false;
		}

		Status status = statusRepository.findByStatus("Da Xac Nhan");

		order.setStatus(status);

		orderRepository.save(order);

		return true;
	}

	public Order findOrderById(int id) {
		Optional<Order> orOptional = orderRepository.findById(id);

		return orOptional.isPresent() ? orOptional.get() : null;
	}

	public boolean confirmOrder(int id, boolean status) {
		Optional<Order> orOptional = orderRepository.findById(id);

		Order order = orOptional.isPresent() ? orOptional.get() : null;

		if (order == null) {
			return false;
		}

		Status st = null;
		
		if(status) {
			st = statusRepository.findByStatus("Giao Hang Thanh Cong");
		} else {
			st = statusRepository.findByStatus("Giao Hang That Bai");
		}

		order.setStatus(st);

		orderRepository.save(order);

		return true;
	}

}
