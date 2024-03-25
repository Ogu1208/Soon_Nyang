package com.ogu.soonnyang.domain.post.repository;

import com.ogu.soonnyang.domain.post.dto.PostListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface PostCustomRepository {

    Page<PostListResponse> findAllNotDeleted(Pageable pageable);
}
