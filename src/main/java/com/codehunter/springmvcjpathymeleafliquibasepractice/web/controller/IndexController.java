package com.codehunter.springmvcjpathymeleafliquibasepractice.web.controller;

import com.codehunter.springmvcjpathymeleafliquibasepractice.core.api.IUserService;
import com.codehunter.springmvcjpathymeleafliquibasepractice.domain.User;
import com.codehunter.springmvcjpathymeleafliquibasepractice.web.dto.UserDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class IndexController {

  public static final String CURRENT_USER = "currentUser";
  private final IUserService userService;

  @ModelAttribute("users")
  public List<User> users() {
    return userService.getAllUser();
  }

  @ModelAttribute("isAuthenticated")
  public Boolean isAuthenticated(HttpServletRequest request) {
    var currentUser = request.getSession().getAttribute(CURRENT_USER);
    return currentUser != null;
  }

  @ModelAttribute("user")
  public UserDto getCurrentUser(HttpServletRequest request) {
    var currentUser = request.getSession().getAttribute(CURRENT_USER);
    if (currentUser == null) {
      return UserDto.builder().build();
    }
    return (UserDto) currentUser;
  }

  @GetMapping
  public String getIndex() {
    return "index";
  }

  @PostMapping(value = "create-user")
  public String createUser(UserDto user, HttpServletRequest request) {
    if (userService.isExist(user.getUsername())) {
      var existUser = userService.readUserByName(user.getUsername());
      request.getSession()
          .setAttribute(CURRENT_USER, UserDto.builder().username(existUser.getName()).build());
    } else {
      User userIn = new User();
      userIn.setName(user.getUsername());
      var createdUser = userService.createUser(userIn);
      request.getSession()
          .setAttribute(CURRENT_USER, UserDto.builder().username(createdUser.getName()).build());
    }
    return "redirect:/";
  }

  @GetMapping(value = "logout")
  public String logout(HttpServletRequest request) {
    request.getSession().invalidate();
    return "redirect:/";
  }
}
