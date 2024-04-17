package com.ogu.soonnyang.domain.post.repository;

import com.ogu.soonnyang.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
    Optional<List<Post>> findTop10ByCat_CatIdOrderByCreatedAtDesc(Long catId);

    List<Post> findByCat_CatIdOrderByCreatedAtDesc(Long catId);

    Optional<Post> findTop1ByCat_CatIdOrderByCreatedAtDesc(Long catId);

    // member ID로 작성한 Post 개수 조회
    Long countByMember_MemberId(Long memberId);
}
