package com.codehunter.springmvcjpathymeleafliquibasepractice.core.api;

import com.codehunter.springmvcjpathymeleafliquibasepractice.domain.Post;
import java.util.List;

public interface IPostService {
  List<Post> getAllPost();
  Post cratePost(Post post);
}
