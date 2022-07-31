package com.codehunter.springmvcjpathymeleafliquibasepractice.web.controller;

import com.codehunter.springmvcjpathymeleafliquibasepractice.core.api.IUserService;
import com.codehunter.springmvcjpathymeleafliquibasepractice.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class IndexController {
  private final IUserService userService;

  @ModelAttribute("users")
  public List<User> users() {
    return userService.getAllUser();
  }

  @GetMapping
  public String getIndex() {
    return "index";
  }
}
