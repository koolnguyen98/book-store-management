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
	
	@Query(value="SELECT COUNT(*) AS quantity, SUM(inv.total_price) AS totalPrice, DATE_FORMAT(inv.date_create, '%Y-%m-%d') AS period FROM bill_order inv \r\n" + 
			"WHERE CAST(inv.date_create AS DATE) >= ?1 \r\n" + 
			"AND CAST(inv.date_create AS DATE) <= ?2 \r\n" + 
			"AND inv.status_id = 3 \r\n" +
			"GROUP BY DATE_FORMAT(inv.date_create, '%Y-%m-%d') ORDER BY inv.date_create ASC", nativeQuery=true)
	List<Revenue> getRevenueByDay(String from, String to);
	
	@Query(value="SELECT COUNT(*) AS quantity, SUM(inv.total_price) AS totalPrice, DATE_FORMAT(inv.date_create, '%Y-%m') AS period FROM bill_order inv \r\n" + 
			"WHERE CAST(inv.date_create AS DATE) >= ?1 \r\n" + 
			"AND CAST(inv.date_create AS DATE) <= ?2 \r\n" + 
			"AND inv.status_id = 3 \r\n" +
			"GROUP BY DATE_FORMAT(inv.date_create, '%Y-%m') ORDER BY inv.date_create ASC", nativeQuery=true)
	List<Revenue> getRevenueByMonth(String from, String to);
	
	@Query(value="SELECT COUNT(*) AS quantity, SUM(inv.total_price) AS totalPrice, DATE_FORMAT(inv.date_create, '%Y') AS period FROM bill_order inv \r\n" + 
			"WHERE CAST(inv.date_create AS DATE) >= ?1 \r\n" + 
			"AND CAST(inv.date_create AS DATE) <= ?2 \r\n" + 
			"AND inv.status_id = 3 \r\n" +
			"GROUP BY DATE_FORMAT(inv.date_create, '%Y') ORDER BY inv.date_create ASC", nativeQuery=true)
	List<Revenue> getRevenueByYear(String from, String to);

}
