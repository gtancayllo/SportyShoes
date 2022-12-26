package com.sportyshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sportyshoes.entity.Category;
import com.sportyshoes.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{  
	
	User findByEmail(String email);
	
	@Query("select u from User u where u.username=:username and u.password=:password")
	List<User> findUserByUserNamePassword(@Param("username") String username, @Param("password") String password);
	
	@Query("select u from User u where u.signedUp=1 and role='User'")
	List<User> findUserSignedUp();
}