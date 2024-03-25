package com.ogu.soonnyang.domain.post.controller;

import com.ogu.soonnyang.domain.cat.controller.CatController;
import com.ogu.soonnyang.domain.post.dto.CreatePostRequest;
import com.ogu.soonnyang.domain.post.dto.PostListResponse;
import com.ogu.soonnyang.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class PostController {

    private final Logger LOGGER = LoggerFactory.getLogger(CatController.class);
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Long> create(
            @RequestPart(value = "post") CreatePostRequest createPostRequest,
            @RequestPart(value = "files", required = false) List<MultipartFile> multipartFiles) {
//        Claims claims = tokenUtils.getClaimsFromRequest(request);
//        UUID memberId = UUID.fromString(claims.get("member_id").toString());
        Long memberId = 1L;

        // 게시물 등록
        Long postId = postService.createBoard(memberId, multipartFiles, createPostRequest);

        return ResponseEntity.created(URI.create("/v1/posts/" + postId)).build();
    }

    @GetMapping
    public ResponseEntity<Page<PostListResponse>> searchAllBoard(@PageableDefault(size = 100) Pageable pageable) {
//        Claims claims = tokenUtils.getClaimsFromRequest(request);
//        UUID memberId = UUID.fromString(claims.get("member_id").toString());
        Long memberId = 1L;

        return ResponseEntity.ok().body(postService.searchAllPost(memberId, pageable));
    }

}
