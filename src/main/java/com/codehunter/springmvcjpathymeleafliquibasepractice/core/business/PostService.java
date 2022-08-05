package com.codehunter.springmvcjpathymeleafliquibasepractice.core.business;

import com.codehunter.springmvcjpathymeleafliquibasepractice.core.api.IPostService;
import com.codehunter.springmvcjpathymeleafliquibasepractice.domain.Post;
import com.codehunter.springmvcjpathymeleafliquibasepractice.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {
  private final PostRepository postRepository;

  @Override
  public List<Post> getAllPost() {
    return postRepository.findAll();
  }

  @Override
  public Post createPost(Post post) {
    return postRepository.save(post);
  }
}
