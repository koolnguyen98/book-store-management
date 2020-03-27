package com.esdc.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>  {

}
