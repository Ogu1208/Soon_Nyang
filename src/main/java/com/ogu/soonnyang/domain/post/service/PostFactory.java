package com.ogu.soonnyang.domain.post.service;

import com.ogu.soonnyang.domain.cat.dto.CreateCatRequest;
import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.repository.CatRepository;
import com.ogu.soonnyang.domain.member.entity.Member;
import com.ogu.soonnyang.domain.post.dto.CreatePostRequest;
import com.ogu.soonnyang.domain.post.entity.Post;
import com.ogu.soonnyang.domain.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostFactory {

    private final PostRepository postRepository;

    @Transactional
    public Post save(CreatePostRequest request, Member member, Cat cat, String imgUrl) {

        Post post = Post.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .content(request.getContent())
                .image(imgUrl)
                .member(member)
                .cat(cat)
                .build();

        postRepository.save(post);

        return post;
    }
}
