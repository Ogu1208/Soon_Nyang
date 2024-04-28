package com.ogu.soonnyang.domain.post.service;

import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.repository.CatRepository;
import com.ogu.soonnyang.domain.file.FileService;
import com.ogu.soonnyang.domain.member.entity.Member;
import com.ogu.soonnyang.domain.member.repository.MemberRepository;
import com.ogu.soonnyang.domain.post.dto.CreatePostRequest;
import com.ogu.soonnyang.domain.post.dto.PostResponse;
import com.ogu.soonnyang.domain.post.entity.Post;
import com.ogu.soonnyang.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final Logger LOGGER = LoggerFactory.getLogger(PostService.class);
    private final CatRepository catRepository;
    private final FileService fileService;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostFactory postFactory;

    /* POST) 게시글 생성 */
    @Transactional
    public Long createPostWithImages(Long member_id, List<MultipartFile> multipartFiles, CreatePostRequest createPostRequest){
        Member member = memberRepository.findById(member_id)
                .orElseThrow(() -> new IllegalArgumentException("사용자 ID 확인해달라 냥!"));
        Cat cat = catRepository.findById(createPostRequest.getCatId())
                .orElseThrow(() -> new IllegalArgumentException("고양이 ID 확인해달라 냥!"));

        Post post = postFactory.save(createPostRequest, member, cat);
        Post savedPost = postRepository.findById(post.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 Post 를 찾을 수 없습니다. postId: " + post.getPostId()));

        // TODO: post가 null일 경우 예외처리
        if (savedPost == null) {
            throw new NotFoundException("post가 정상적으로 생성되지 않았습니다.");
        }

        // multipartFiles가 비어있지 않은 경우 s3 업로드
        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            fileService.uploadPostImages(multipartFiles, savedPost);
        }

        return savedPost.getPostId();
    }

    /* GET) 게시글 리스트 조회 readOnly 속성으로 조회속도 개선 */
    @Transactional(readOnly = true)
    public Page<PostResponse> searchAllPost(Long memberId, Pageable pageable) {
        Page<PostResponse> page = postRepository.findAllNotDeleted(pageable);
        List<PostResponse> postRespons = page.getContent();

        for (PostResponse postListRespons : postRespons) {
            postListRespons.setMyEmotion("like");
            postListRespons.setMyEmotion(PostResponse.randomLikeOrUnlike());
            postListRespons.setLikeCount(PostResponse.randomLikeCount());
            // TODO: 직접 가져와서 좋아요 여부, 좋아요 개수 setting
//            postListRespons.setMyEmotion(postLikeRepository.checkReation(memberId, postListRespons.getPostId()));
        }

        return page;
    }

    /* GET) 해당 id로 post 조회 */
    @Transactional(readOnly = true)
    public PostResponse getPostByPostId(Long memberId, Long postId) {

        return postRepository.getPostResponseById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 Post 를 찾을 수 없습니다. postId: " + postId));
    }
}
