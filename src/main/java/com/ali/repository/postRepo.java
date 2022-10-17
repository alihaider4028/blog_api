package com.ali.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ali.entity.post;
@Repository
public interface postRepo extends JpaRepository<post, Integer> {
@Query("select p from post p where p.user.id = :userID")
List<post> findbyUser(@Param("userID") Integer user);
@Query("select p from post p where p.category.categoryId = :categoryid")
List<post> findbyCategory(@Param("categoryid") Integer categoryid);


}
