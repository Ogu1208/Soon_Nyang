package com.ogu.soonnyang.domain.post.controller;

import com.ogu.soonnyang.domain.post.dto.CreatePostRequest;
import com.ogu.soonnyang.domain.post.dto.PostResponse;
import com.ogu.soonnyang.domain.post.service.PostService;
import com.ogu.soonnyang.util.TokenUtils;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@Slf4j
@Tag(name = "Posts", description = "Posts 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService postService;
    private final TokenUtils tokenUtils;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Long> create(
            HttpServletRequest request,
            @RequestPart(value = "post") CreatePostRequest createPostRequest,
            @RequestPart(value = "files", required = false) List<MultipartFile> multipartFiles) {
        Claims claims = tokenUtils.getClaimsFromRequest(request);
        Long memberId = (Long) claims.get("member_id");
//        Long memberId = 1L;

        // 게시물 등록
        Long postId = postService.createPostWithImages(memberId, multipartFiles, createPostRequest);

        log.info("게시물 등록 완료. 게시물 ID: {}", postId);
        return ResponseEntity.created(URI.create("/v1/posts/" + postId)).build();
    }

    @Operation(operationId = "Post List", summary = "Post 리스트 조회", description = "Post List를 상세 조회한다.")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<PostResponse>> searchAllBoard(
            HttpServletRequest request,
            @PageableDefault(size = 100) Pageable pageable) {
        Claims claims = tokenUtils.getClaimsFromRequest(request);
        Long memberId = (Long) claims.get("member_id");
//        Long memberId = 1L;

        return ResponseEntity.ok().body(postService.searchAllPost(memberId, pageable));
    }

    @Operation(operationId = "postDetail", summary = "게시글 단건 조회", description = "postId 로 단건 조회한다.")
    @GetMapping("/{postId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PostResponse> getPost(HttpServletRequest request, @PathVariable("postId") Long catId) {
        Claims claims = tokenUtils.getClaimsFromRequest(request);
        Long memberId = (Long) claims.get("member_id");
//        Long memberId = 1L;

        return ResponseEntity.ok().body(postService.getPostByPostId(memberId, catId));
    }
}
