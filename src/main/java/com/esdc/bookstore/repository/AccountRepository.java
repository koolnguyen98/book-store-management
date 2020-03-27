package com.esdc.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>  {

	Account findByUserName(String userName);

}
