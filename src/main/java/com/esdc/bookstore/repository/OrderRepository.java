package com.esdc.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Order;
import com.esdc.bookstore.entity.Status;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>  {

	List<Order> findByStatus(Status status);

}
