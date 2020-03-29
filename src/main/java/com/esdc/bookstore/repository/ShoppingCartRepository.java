package com.esdc.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Product;
import com.esdc.bookstore.entity.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>  {

	List<ShoppingCart> findByProduct(Product product);

}
