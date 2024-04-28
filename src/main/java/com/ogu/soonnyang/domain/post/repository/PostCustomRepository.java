package com.ogu.soonnyang.domain.post.repository;

import com.ogu.soonnyang.domain.post.dto.PostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Repository
public interface PostCustomRepository {
    Page<PostResponse> findAllNotDeleted(Pageable pageable);
    Optional<PostResponse> getPostResponseById(Long postId);
}
