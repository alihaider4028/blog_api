package com.ali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ali.entity.Roles;
@Repository
public interface rolesRepo extends JpaRepository<Roles, Integer> {

}
