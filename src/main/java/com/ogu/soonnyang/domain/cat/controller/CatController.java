package com.ogu.soonnyang.domain.cat.controller;

import com.ogu.soonnyang.common.dto.MessageDTO;
import com.ogu.soonnyang.domain.cat.dto.CatDetailResponse;
import com.ogu.soonnyang.domain.cat.dto.CatRequest;
import com.ogu.soonnyang.domain.cat.dto.CatListResponse;
import com.ogu.soonnyang.domain.cat.service.CatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cats")
public class CatController {
    private final Logger LOGGER = LoggerFactory.getLogger(CatController.class);
    private final CatService catService;

    // 고양이 등록
    @Transactional
    @PostMapping
    public ResponseEntity<Long> insertCat(
            @RequestPart(value = "cat") CatRequest catRequest,
            @RequestPart(value = "file", required = false) MultipartFile image) {
        LOGGER.info("=========================insertCat===================================");
        Long memberId = 1L;
        final Long catId = catService.save(memberId, catRequest, image);
        return ResponseEntity.created(URI.create("/v1/cats/" + catId)).build();
    }

    // 고양이 리스트 조회
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<CatListResponse>> getCats() {
        Long memberId = 1L;
        List<CatListResponse> catListResponses = catService.getCats(memberId);
        return ResponseEntity.ok().body(catListResponses);
    }

    // 특정 고양이 상세 조회
    @Transactional(readOnly = true)
    @GetMapping("/{catId}")
    public ResponseEntity<CatDetailResponse> getCat(@PathVariable("catId") Long catId) {
        CatDetailResponse catDetailResp = catService.getCat(catId);
        return ResponseEntity.ok().body(catDetailResp);
    }

    // 특정 고양이 수정
    @Transactional
    @PutMapping("/{catId}")
    public ResponseEntity<MessageDTO> updateCat(
            @PathVariable("catId") Long catId,
            @RequestPart(value = "cat") CatRequest catRequest,
            @RequestPart(value = "file", required = false) MultipartFile image) {

        catService.updateCat(catId, catRequest, image);

        MessageDTO messageDTO = MessageDTO.builder()
                .status(HttpStatus.OK.value())
                .message("catId : " + catId + " 가 정상적으로 수정되었습니다.")
                .redirectURI(null)
                .build();

        return ResponseEntity.ok().body(messageDTO);
    }

    // 특정 고양이 삭제
    @Transactional
    @DeleteMapping("/{catId}")
    public ResponseEntity<MessageDTO> deleteCat(@PathVariable("catId") Long catId) {
        catService.deleteCat(catId);

        MessageDTO messageDTO = MessageDTO.builder()
                .status(HttpStatus.OK.value())
                .message("catId : " + catId + " 가 정상적으로 삭제되었습니다.")
                .redirectURI(null)
                .build();

        return ResponseEntity.ok().body(messageDTO);
    }
}