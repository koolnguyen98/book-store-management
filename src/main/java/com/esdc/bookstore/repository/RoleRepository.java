package com.esdc.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esdc.bookstore.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>  {

}
