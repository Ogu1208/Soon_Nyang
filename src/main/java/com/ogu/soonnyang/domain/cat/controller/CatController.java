package com.ogu.soonnyang.domain.cat.controller;

import com.ogu.soonnyang.domain.cat.dto.CatDetailResponse;
import com.ogu.soonnyang.domain.cat.dto.CreateCatRequest;
import com.ogu.soonnyang.domain.cat.dto.CatListResponse;
import com.ogu.soonnyang.domain.cat.service.CatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cats")
public class CatController {
    private final Logger LOGGER = LoggerFactory.getLogger(CatController.class);
    private final CatService catService;

    // 고양이 등록
    @PostMapping
    public ResponseEntity<Long> insertCat(
            @RequestPart(value = "cat") CreateCatRequest createCatRequest,
            @RequestPart(value = "file", required = false) MultipartFile image) {
        LOGGER.info("=========================insertCat===================================");
        Long memberId = 1L;
        final Long catId = catService.save(memberId, createCatRequest, image);
        return ResponseEntity.created(URI.create("/api/cats/" + catId)).build();
    }

    // 고양이 리스트 조회
    @GetMapping
    public ResponseEntity<List<CatListResponse>> getCats() {
        Long memberId = 1L;
        List<CatListResponse> catListResponses = catService.getCats(memberId);
        return ResponseEntity.ok().body(catListResponses);
    }

    // 특정 고양이 상세 조회
    @GetMapping("/{catId}")
    public ResponseEntity<CatDetailResponse> getCat(@PathVariable("catId") Long catId) {
        CatDetailResponse catDetailResp = catService.getCat(catId);
        return ResponseEntity.ok().body(catDetailResp);
    }
}