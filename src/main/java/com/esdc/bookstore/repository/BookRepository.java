package com.esdc.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Author;
import com.esdc.bookstore.entity.Book;
import com.esdc.bookstore.entity.ProductType;
import com.esdc.bookstore.entity.PublishingCompany;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>  {

	List<Book> findByAuthor(Author author);

	List<Book> findByPublishingCompany(PublishingCompany publishingCompany);

	List<Book> findByProductType(ProductType productType);

	@Query(value = "SELECT b FROM Book b WHERE b.productName LIKE %?1%")
	List<Book> findBookByName(String textSearch);

}
