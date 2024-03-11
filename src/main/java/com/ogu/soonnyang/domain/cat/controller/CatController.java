package com.ogu.soonnyang.domain.cat.controller;

import com.ogu.soonnyang.domain.cat.dto.CatDetailResponse;
import com.ogu.soonnyang.domain.cat.dto.CatInfoRequest;
import com.ogu.soonnyang.domain.cat.dto.CatListResponse;
import com.ogu.soonnyang.domain.cat.service.CatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cats")
public class CatController {
    private final Logger LOGGER = LoggerFactory.getLogger(CatController.class);
    private final CatService catService;

    @PostMapping("")
    public ResponseEntity<Void> insertCat(@RequestBody CatInfoRequest catInfoRequest) {
        LOGGER.info("=========================insertCat===================================");

        final Long catId = catService.save(memberId, catInfoRequest);
        return ResponseEntity.created(URI.create("/api/cats/" + catId)).build();
    }

    // 고양이 리스트 조회
    @GetMapping("")
    public ResponseEntity<List<CatListResponse>> getCat() {
        List<CatListResponse> catListResponses = catService.getCats(catId);
        return ResponseEntity.ok().body(catListResponses);
    }

    // 특정 고양이 상세 조회
    @GetMapping("/{catId}")
    public ResponseEntity<CatDetailResponse> getCat(@PathVariable Long catId) {
        CatDetailResponse catDetailResp = catService.getCat(catId);
        return ResponseEntity.ok().body(catDetailResp);
    }
}