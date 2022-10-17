package com.ali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ali.entity.comment;
@Repository
public interface commentRepo extends JpaRepository<comment,Integer>{

}
