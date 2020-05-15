package com.esdc.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Order;
import com.esdc.bookstore.entity.Revenue;
import com.esdc.bookstore.entity.Status;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>  {

	List<Order> findByStatus(Status status);
	
	@Query(value="SELECT COUNT(*) AS quantity, SUM(inv.total_price) AS totalPrice, DATE_FORMAT(inv.created_date, '%Y-%m-%d') AS period FROM invoice inv \r\n" + 
			"JOIN shopping_cart cart ON inv.cart_id = cart.id \r\n" +
			"WHERE CAST(inv.created_date AS DATE) >= ?1 \r\n" + 
			"AND CAST(inv.created_date AS DATE) <= ?2 \r\n" + 
			"AND cart.is_paid = 1 \r\n" +
			"AND inv.status = 'SUCCEED' \r\n" +
			"GROUP BY DATE_FORMAT(inv.created_date, '%Y-%m-%d') ORDER BY inv.created_date ASC", nativeQuery=true)
	List<Revenue> getRevenueByDay(String from, String to);
	
	@Query(value="SELECT COUNT(*) AS quantity, SUM(inv.total_price) AS totalPrice, DATE_FORMAT(inv.created_date, '%Y-%m') AS period FROM invoice inv \r\n" + 
			"JOIN shopping_cart cart ON inv.cart_id = cart.id \r\n" +
			"WHERE CAST(inv.created_date AS DATE) >= ?1 \r\n" + 
			"AND CAST(inv.created_date AS DATE) <= ?2 \r\n" + 
			"AND cart.is_paid = 1 \r\n" +
			"AND inv.status = 'SUCCEED' \r\n" +
			"GROUP BY DATE_FORMAT(inv.created_date, '%Y-%m') ORDER BY inv.created_date ASC", nativeQuery=true)
	List<Revenue> getRevenueByMonth(String from, String to);
	
	@Query(value="SELECT COUNT(*) AS quantity, SUM(inv.total_price) AS totalPrice, DATE_FORMAT(inv.created_date, '%Y') AS period FROM invoice inv \r\n" + 
			"JOIN shopping_cart cart ON inv.cart_id = cart.id \r\n" +
			"WHERE CAST(inv.created_date AS DATE) >= ?1 \r\n" + 
			"AND CAST(inv.created_date AS DATE) <= ?2 \r\n" + 
			"AND cart.is_paid = 1 \r\n" +
			"AND inv.status = 'SUCCEED' \r\n" +
			"GROUP BY DATE_FORMAT(inv.created_date, '%Y') ORDER BY inv.created_date ASC", nativeQuery=true)
	List<Revenue> getRevenueByYear(String from, String to);

}
