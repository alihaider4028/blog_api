package com.ali.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ali.entity.Users;
@Repository
public interface userRepository extends JpaRepository<Users, Integer> {

	@Query("select p from Users p where p.email = :email")
Optional<Users> findUserBYEmail(@Param("email")String email);
}
