package com.esdc.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Product;
import com.esdc.bookstore.entity.ProductType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>  {

	List<Product> findByProductType(ProductType productType);

}
