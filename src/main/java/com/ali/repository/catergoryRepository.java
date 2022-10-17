package com.ali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ali.entity.category;
@Repository
public interface catergoryRepository extends JpaRepository<category, Integer> {

}
