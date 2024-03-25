package com.ogu.soonnyang.domain.post.service;

import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.repository.CatRepository;
import com.ogu.soonnyang.domain.member.entity.Member;
import com.ogu.soonnyang.domain.member.repository.MemberRepository;
import com.ogu.soonnyang.domain.post.dto.CreatePostRequest;
import com.ogu.soonnyang.domain.post.dto.PostListResponse;
import com.ogu.soonnyang.domain.post.entity.Post;
import com.ogu.soonnyang.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final Logger LOGGER = LoggerFactory.getLogger(PostService.class);
    private final CatRepository catRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostFactory postFactory;

    public Long createBoard(Long member_id, List<MultipartFile> multipartFiles, CreatePostRequest createPostRequest) {
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new IllegalArgumentException("사용자 ID 확인해달라 냥!"));
        Cat cat = catRepository.findById(createPostRequest.getCatId()).orElseThrow(() -> new IllegalArgumentException("고양이 ID 확인해달라 냥!"));

        // S3에 이미지 등록
        String imgUrl = "https://www.fitpetmall.com/wp-content/uploads/2023/09/shutterstock_2205178589-1-1.png";

        Post post = postFactory.save(createPostRequest, member, cat, imgUrl);


        return post.getPostId();
    }

    public Page<PostListResponse> searchAllPost(Long memberId, Pageable pageable) {
        Page<PostListResponse> page = postRepository.findAllNotDeleted(pageable);
        List<PostListResponse> postListResponses = page.getContent();

        for (PostListResponse postListRespons : postListResponses) {
            postListRespons.setMyEmotion("like");
            // TODO: 직접 가져와서 좋아요 여부 setting
//            postListRespons.setMyEmotion(postLikeRepository.checkReation(memberId, postListRespons.getPostId()));
        }

        return page;
    }
}
