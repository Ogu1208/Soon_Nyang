package com.ogu.soonnyang.domain.post.controller;

import com.ogu.soonnyang.domain.cat.controller.CatController;
import com.ogu.soonnyang.domain.post.service.PostImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PostImages", description = "PostImage 관련 API 입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/postImages")
public class PostImageController {

    private final Logger LOGGER = LoggerFactory.getLogger(CatController.class);
    private final PostImageService postImageService;

    @Operation(summary = "게시글의 파일 삭제", description = " {id} 게시글의 {id}파일을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete OK"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticleFile(@PathVariable Long id) {
        postImageService.deletePostImage(id);
        return ResponseEntity.noContent().build();
    }
}
