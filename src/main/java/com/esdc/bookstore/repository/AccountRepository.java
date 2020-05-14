package com.esdc.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Account;
import com.esdc.bookstore.entity.Role;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>  {

	Account findByUserName(String userName);

	@Query(value = "SELECT a.roles FROM Account a WHERE a.id = :id", nativeQuery = true)
	List<Role> getRole(@Param("id") Integer id);
	
	Account findAccountById(Integer id);

}
