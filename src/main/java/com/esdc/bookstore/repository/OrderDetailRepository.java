package com.esdc.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Order;
import com.esdc.bookstore.entity.OrderDetail;
import com.esdc.bookstore.entity.Product;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>  {

	List<OrderDetail> findByProduct(Product product);

	List<OrderDetail> findByOrder(Order order);

}
