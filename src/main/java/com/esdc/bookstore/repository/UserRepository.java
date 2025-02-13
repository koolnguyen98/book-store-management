package com.esdc.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Account;
import com.esdc.bookstore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {

	User findByAccount(Account account);

	User findByEmail(String email);
	
	User findUserByAccountId(Integer id);
}
