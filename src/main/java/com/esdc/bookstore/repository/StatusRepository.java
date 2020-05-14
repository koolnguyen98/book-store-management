package com.esdc.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>  {

	Status findByStatus(String string);

}
