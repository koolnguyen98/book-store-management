package com.esdc.bookstore.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.esdc.bookstore.controller.form.AdditionalForm;
import com.esdc.bookstore.controller.form.BookForm;
import com.esdc.bookstore.controller.form.ProductTypeForm;
import com.esdc.bookstore.controller.form.StationeryForm;
import com.esdc.bookstore.entity.Author;
import com.esdc.bookstore.entity.Book;
import com.esdc.bookstore.entity.Brand;
import com.esdc.bookstore.entity.Image;
import com.esdc.bookstore.entity.OrderDetail;
import com.esdc.bookstore.entity.Product;
import com.esdc.bookstore.entity.ProductType;
import com.esdc.bookstore.entity.PublishingCompany;
import com.esdc.bookstore.entity.ShoppingCart;
import com.esdc.bookstore.entity.Stationery;
import com.esdc.bookstore.repository.AuthorRepository;
import com.esdc.bookstore.repository.BookRepository;
import com.esdc.bookstore.repository.BrandRepository;
import com.esdc.bookstore.repository.ImageRepository;
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
	
	@Autowired
	private ImageRepository imageRepository;

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
		
		Book result = bookRepository.save(book);
		
		List<Image> images = this.getByteListImage(bookForm.getImageFiles(), result);
		
		imageRepository.saveAll(images);
		
		return bookRepository.findById(result.getId()).get();
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
			bookForm.setBase64Images(this.CoverBase64(book.getImages()));

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
			
			Book result = bookRepository.save(book);
			
			List<Image> images = this.getByteListImage(bookForm.getImageFiles(), result);
			
			imageRepository.saveAll(images);
			
			return bookRepository.findById(result.getId()).get();
		}
		return null;
	}

	public Boolean deleteBookById(int id) {
		Optional<Book> bookOpt = bookRepository.findById(id);
		Book book = bookOpt.isPresent() ? bookOpt.get() : null;
		Optional<Product> productOtp = productRepository.findById(id);
		Product product = productOtp.isPresent() ? productOtp.get() : null;
		if (book != null && product != null) {
			List<ShoppingCart> spcbooks = shoppingCartRepository.findByShoppingCartKeyProductId(product.getId());
			List<OrderDetail> oddbooks = orderDetailRepository.findByProduct(product);
			
			if(spcbooks.isEmpty() && oddbooks.isEmpty()) {
				List<Image> images = book.getImages();
				
				if (!images.isEmpty()) {
					imageRepository.deleteInBatch(images);
				}
				List<Book> books = new ArrayList<Book>();
				books.add(book);
				bookRepository.deleteInBatch(books);

				return true;
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
		
		Stationery result = stationeryRepository.save(stationery);
		
		List<Image> images = this.getByteListImage(stationeryForm.getImageFiles(), result);
		
		imageRepository.saveAll(images);
		
		return stationeryRepository.findById(result.getId()).get();
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
			stationeryForm.setBase64Images(this.CoverBase64(stationery.getImages()));
			
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

			Stationery result = stationeryRepository.save(stationery);
			
			List<Image> images = this.getByteListImage(stationeryForm.getImageFiles(), result);
			
			imageRepository.saveAll(images);
			
			return stationeryRepository.findById(result.getId()).get();
		}
		return null;
	}

	public Boolean deleteStationeryById(int id) {
		Optional<Stationery> stationeryOpt = stationeryRepository.findById(id);
		Stationery stationery = stationeryOpt.isPresent() ? stationeryOpt.get() : null;
		Optional<Product> productOtp = productRepository.findById(id);
		Product product = productOtp.isPresent() ? productOtp.get() : null;
		if (stationery != null && product != null) {
			List<ShoppingCart> spcStationerys = shoppingCartRepository.findByShoppingCartKeyProductId(product.getId());
			List<OrderDetail> oddStationerys = orderDetailRepository.findByProduct(product);

			if (spcStationerys.isEmpty() && oddStationerys.isEmpty()) {
				List<Image> images = stationery.getImages();

				if (images.isEmpty()) {
					imageRepository.deleteAll(images);
				}

				stationeryRepository.delete(stationery);
			}
			stationery.setStatus(false);
			stationeryRepository.save(stationery);
			return true;
		}
		return false;
	}
	
	private List<Image> getByteListImage(MultipartFile[] imageFiles, Product result) {
		List<Image> images = new ArrayList<Image>();
		
		if (imageFiles != null && imageFiles.length > 0) {
			for (MultipartFile imageFile : imageFiles) {
				Image image = new Image();
				try {
					image.setImage(imageFile.getBytes());
					image.setBase64(Base64.getEncoder().encodeToString(imageFile.getBytes()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				image.setProduct(result);
				images.add(image);
			}
			return images;
		}
		
		return null;
	}
	
	private List<String> CoverBase64(List<Image> images) {
		List<String> base64Images = new ArrayList<String>();
		for (Image image : images) {
			String base64Encoded = Base64.getEncoder().encodeToString(image.getImage());
			base64Images.add(base64Encoded);
		}
		return base64Images;
	}

	public ProductType insertProductType(ProductTypeForm productTypeForm) {
		ProductType productType = new ProductType();

		productType.setAcronym(productTypeForm.getAcronym());
		productType.setName(productTypeForm.getName());

		return productTypeRepository.save(productType);
	}
	
	public ProductTypeForm findProductTypeById(int id) {
		Optional<ProductType> productTypeOpt = productTypeRepository.findById(id);
		ProductType productType = productTypeOpt.isPresent() ? productTypeOpt.get() : null;
		if (productType != null) {
			ProductTypeForm productTypeForm = new ProductTypeForm();
			productTypeForm.setId(productType.getId());
			productTypeForm.setAcronym(productType.getAcronym());
			productTypeForm.setName(productType.getName());

			return productTypeForm;
		}
		return null;
	}

	public ProductType updateProductType(ProductTypeForm productTypeForm) {
		Optional<ProductType> productTypeOpt = productTypeRepository.findById(productTypeForm.getId());
		ProductType productType = productTypeOpt.isPresent() ? productTypeOpt.get() : null;
		if (productType != null) {
			productType.setAcronym(productTypeForm.getAcronym());
			productType.setName(productTypeForm.getName());

			return productTypeRepository.save(productType);
		}
		return null;
	}

	public Boolean deleteProductTypeById(int id) {
		Optional<ProductType> productTypeOpt = productTypeRepository.findById(id);
		ProductType productType = productTypeOpt.isPresent() ? productTypeOpt.get() : null;
		if (productType != null) {
			List<Product> products = productRepository.findByProductType(productType);
			
			if(products.isEmpty()) {
				productTypeRepository.delete(productType);
				return true;
			}
		}
		return false;
	}

	public Boolean insertAdditionalProperties(AdditionalForm additionalForm) {
		switch (additionalForm.getType()) {
		case "BRAND":
			Brand brand = new Brand();

			brand.setCountry(additionalForm.getCountry());
			brand.setName(additionalForm.getName());
			if (brandRepository.save(brand) != null) {
				return true;
			}
			break;

		case "AUTHOR":
			Author author = new Author();

			author.setCountry(additionalForm.getCountry());
			author.setName(additionalForm.getName());
			if (authorRepository.save(author) != null) {
				return true;
			}
			break;

		case "PUBLISHINGCOMPANY":
			PublishingCompany publishingCompany = new PublishingCompany();

			publishingCompany.setCountry(additionalForm.getCountry());
			publishingCompany.setName(additionalForm.getName());
			if (publishingCompanyRepository.save(publishingCompany) != null) {
				return true;
			}
			break;

		default:
			break;
		}
		

		return false;
	}

	public AdditionalForm findAdditionalByIdAndType(int id, String type) {
		switch (type) {
		case "BRAND":
			Optional<Brand> brandOpt = brandRepository.findById(id);
			Brand brand = brandOpt.isPresent() ? brandOpt.get() : null;
			if (brand != null) {
				AdditionalForm additionalForm = new AdditionalForm();
				additionalForm.setId(brand.getId());
				additionalForm.setCountry(brand.getCountry());
				additionalForm.setName(brand.getName());

				return additionalForm;
			}
			break;

		case "AUTHOR":
			Optional<Author> authorOpt = authorRepository.findById(id);
			Author author = authorOpt.isPresent() ? authorOpt.get() : null;
			if (author != null) {
				AdditionalForm additionalForm = new AdditionalForm();
				additionalForm.setId(author.getId());
				additionalForm.setCountry(author.getCountry());
				additionalForm.setName(author.getName());

				return additionalForm;
			}
			break;

		case "PUBLISHINGCOMPANY":
			Optional<PublishingCompany> publishingCompanyOpt = publishingCompanyRepository.findById(id);
			PublishingCompany publishingCompany = publishingCompanyOpt.isPresent() ? publishingCompanyOpt.get() : null;
			if (publishingCompany != null) {
				AdditionalForm additionalForm = new AdditionalForm();
				additionalForm.setId(publishingCompany.getId());
				additionalForm.setCountry(publishingCompany.getCountry());
				additionalForm.setName(publishingCompany.getName());

				return additionalForm;
			}
			break;

		default:
			break;
		}

		return null;
	}

	public Boolean updateAdditional(@Valid AdditionalForm additionalForm) {
		switch (additionalForm.getType()) {
		case "BRAND":
			Optional<Brand> brandOpt = brandRepository.findById(additionalForm.getId());
			Brand brand = brandOpt.isPresent() ? brandOpt.get() : null;
			if (brand != null) {
				brand.setCountry(additionalForm.getCountry());
				brand.setName(additionalForm.getName());
				if (brandRepository.save(brand) != null) {
					return true;
				}
			}
			break;

		case "AUTHOR":
			Optional<Author> authorOpt = authorRepository.findById(additionalForm.getId());
			Author author = authorOpt.isPresent() ? authorOpt.get() : null;
			if (author != null) {
				author.setCountry(additionalForm.getCountry());
				author.setName(additionalForm.getName());
				if (authorRepository.save(author) != null) {
					return true;
				}
			}
			break;

		case "PUBLISHINGCOMPANY":
			Optional<PublishingCompany> publishingCompanyOpt = publishingCompanyRepository.findById(additionalForm.getId());
			PublishingCompany publishingCompany = publishingCompanyOpt.isPresent() ? publishingCompanyOpt.get() : null;
			if (publishingCompanyOpt != null) {
				publishingCompany.setCountry(additionalForm.getCountry());
				publishingCompany.setName(additionalForm.getName());
				if (publishingCompanyRepository.save(publishingCompany) != null) {
					return true;
				}
			}
			break;

		default:
			break;
		}
		
		return false;
	}

	public Boolean deleteAdditionalByIdAndType(int id, String type) {
		switch (type) {
		case "BRAND":
			Optional<Brand> brandOpt = brandRepository.findById(id);
			Brand brand = brandOpt.isPresent() ? brandOpt.get() : null;
			if (brand != null) {
				List<Stationery> stationeries = stationeryRepository.findByBrand(brand);
				if(stationeries.isEmpty()) {
					brandRepository.delete(brand);
					return true;
				}
			}
			break;

		case "AUTHOR":
			Optional<Author> authorOpt = authorRepository.findById(id);
			Author author = authorOpt.isPresent() ? authorOpt.get() : null;
			if (author != null) {
				List<Book> books = bookRepository.findByAuthor(author);
				if(books.isEmpty()) {
					authorRepository.delete(author);
					return true;
				}
			}
			break;

		case "PUBLISHINGCOMPANY":
			Optional<PublishingCompany> publishingCompanyOpt = publishingCompanyRepository.findById(id);
			PublishingCompany publishingCompany = publishingCompanyOpt.isPresent() ? publishingCompanyOpt.get() : null;
			if (publishingCompanyOpt != null) {
				List<Book> books = bookRepository.findByPublishingCompany(publishingCompany);
				if(books.isEmpty()) {
					publishingCompanyRepository.delete(publishingCompany);
					return true;
				}
			}
			break;

		default:
			break;
		}
		
		return false;
	}
}
