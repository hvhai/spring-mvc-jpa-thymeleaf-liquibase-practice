package com.codehunter.springmvcjpathymeleafliquibasepractice.web.controller;

import com.codehunter.springmvcjpathymeleafliquibasepractice.core.api.IPostService;
import com.codehunter.springmvcjpathymeleafliquibasepractice.core.api.IUserService;
import com.codehunter.springmvcjpathymeleafliquibasepractice.domain.Post;
import com.codehunter.springmvcjpathymeleafliquibasepractice.domain.User;
import com.codehunter.springmvcjpathymeleafliquibasepractice.web.dto.UserDto;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
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
  private final IPostService postService;

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
    var user = (User) currentUser;
    return UserDto.builder().username(user.getName()).build();
  }

  @ModelAttribute("allPost")
  public List<Post> allPost(){
    return postService.getAllPost();
  }

  @GetMapping
  public String getIndex() {
    return "index";
  }

  @PostMapping(value = "create-user")
  public String createUser(UserDto user, HttpServletRequest request) {
    if (userService.isExist(user.getUsername())) {
      var existUser = userService.readUserByName(user.getUsername());
      request.getSession().setAttribute(CURRENT_USER, existUser);
    } else {
      User userIn = new User();
      userIn.setName(user.getUsername());
      var createdUser = userService.createUser(userIn);
      request.getSession().setAttribute(CURRENT_USER, createdUser);
    }
    return "redirect:/";
  }

  @GetMapping(value = "logout")
  public String logout(HttpServletRequest request) {
    request.getSession().invalidate();
    return "redirect:/";
  }

  @PostMapping(value = "create-post")
  public String createPost(String content, HttpServletRequest request) {
    var currentUser = request.getSession().getAttribute(CURRENT_USER);
    Post post = new Post();
    post.setContent(content);
    post.setUser(
        Optional.ofNullable(request.getSession().getAttribute(CURRENT_USER))
            .filter(User.class::isInstance)
            .map(User.class::cast)
            .orElse(null));
    post.setCreatedTime(Instant.now());
    postService.createPost(post);
    return "redirect:/";
  }

}
