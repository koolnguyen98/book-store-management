package com.esdc.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>  {

}
