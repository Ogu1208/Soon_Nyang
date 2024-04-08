package com.ogu.soonnyang.domain.post.controller;

import com.ogu.soonnyang.domain.cat.controller.CatController;
import com.ogu.soonnyang.domain.post.dto.CreatePostRequest;
import com.ogu.soonnyang.domain.post.dto.PostListResponse;
import com.ogu.soonnyang.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Posts", description = "Posts 관련 API 입니다.")
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

    @Operation(operationId = "Post List", summary = "Post 리스트 조회", description = "Post List를 상세 조회한다.")
    @GetMapping
    public ResponseEntity<Page<PostListResponse>> searchAllBoard(@PageableDefault(size = 100) Pageable pageable) {
//        Claims claims = tokenUtils.getClaimsFromRequest(request);
//        UUID memberId = UUID.fromString(claims.get("member_id").toString());
        Long memberId = 1L;

        return ResponseEntity.ok().body(postService.searchAllPost(memberId, pageable));
    }

}
