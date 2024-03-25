package com.ogu.soonnyang.domain.post.controller;

import com.ogu.soonnyang.domain.cat.controller.CatController;
import com.ogu.soonnyang.domain.post.dto.CreatePostRequest;
import com.ogu.soonnyang.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

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


}
