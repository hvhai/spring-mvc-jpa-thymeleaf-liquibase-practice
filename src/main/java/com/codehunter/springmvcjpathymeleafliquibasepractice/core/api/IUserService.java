package com.codehunter.springmvcjpathymeleafliquibasepractice.core.api;

import com.codehunter.springmvcjpathymeleafliquibasepractice.domain.User;
import java.util.List;

public interface IUserService {
  List<User> getAllUser();
}
