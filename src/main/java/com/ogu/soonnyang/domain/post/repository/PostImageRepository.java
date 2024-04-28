package com.ogu.soonnyang.domain.post.repository;

import com.ogu.soonnyang.domain.post.entity.Post;
import com.ogu.soonnyang.domain.post.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long> {

    void deleteByPost(Post post);

    List<PostImage> findByPost(Post post);
}
