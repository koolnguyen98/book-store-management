package com.esdc.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.PublishingCompany;

@Repository
public interface PublishingCompanyRepository extends JpaRepository<PublishingCompany, Integer>  {

}
