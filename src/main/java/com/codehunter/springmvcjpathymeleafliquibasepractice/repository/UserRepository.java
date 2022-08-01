package com.codehunter.springmvcjpathymeleafliquibasepractice.repository;

import com.codehunter.springmvcjpathymeleafliquibasepractice.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  List<User> findDistinctByName(String name);
}
