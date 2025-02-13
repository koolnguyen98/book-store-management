package com.esdc.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>  {

}
