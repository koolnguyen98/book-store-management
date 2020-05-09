package com.esdc.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>  {

	ProductType findByAcronym(String acronym);

}
