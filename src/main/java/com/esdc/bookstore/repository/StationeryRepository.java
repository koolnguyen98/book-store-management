package com.esdc.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Brand;
import com.esdc.bookstore.entity.Stationery;

@Repository
public interface StationeryRepository extends JpaRepository<Stationery, Integer>  {

	List<Stationery> findByBrand(Brand brand);

}
