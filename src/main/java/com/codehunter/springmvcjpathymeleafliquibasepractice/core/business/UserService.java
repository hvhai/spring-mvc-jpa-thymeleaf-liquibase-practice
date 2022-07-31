package com.codehunter.springmvcjpathymeleafliquibasepractice.core.business;

import com.codehunter.springmvcjpathymeleafliquibasepractice.core.api.IUserService;
import com.codehunter.springmvcjpathymeleafliquibasepractice.domain.User;
import com.codehunter.springmvcjpathymeleafliquibasepractice.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
  private final UserRepository userRepository;


  @Override
  public List<User> getAllUser() {
    return userRepository.findAll();
  }
}
