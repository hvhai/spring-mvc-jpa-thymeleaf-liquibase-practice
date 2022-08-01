package com.codehunter.springmvcjpathymeleafliquibasepractice.core.business;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

import com.codehunter.springmvcjpathymeleafliquibasepractice.core.api.IUserService;
import com.codehunter.springmvcjpathymeleafliquibasepractice.domain.User;
import com.codehunter.springmvcjpathymeleafliquibasepractice.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final UserRepository userRepository;


  @Override
  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  @Override
  public Boolean isExist(String username) {
    var user = new User();
    user.setName(username);
    return userRepository.exists(Example.of(user,
        ExampleMatcher.matching().withIgnorePaths("id").withMatcher("username", exact())));
  }

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User readUserByName(String username) {
    var user = new User();
    user.setName(username);
    return userRepository.findDistinctByName(username).stream().findFirst().orElseThrow();
  }
}
