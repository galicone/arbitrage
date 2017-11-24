package com.crypto.arbitrage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crypto.arbitrage.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "select 1 from public.user where username=?", nativeQuery = true)
	User findByUsername(String username);

	@Query(value = "select * from public.user", nativeQuery = true)
	List<User> findByEmails();
}