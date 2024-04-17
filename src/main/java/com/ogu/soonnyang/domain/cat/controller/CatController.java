package com.ogu.soonnyang.domain.cat.controller;

import com.ogu.soonnyang.common.dto.MessageDTO;
import com.ogu.soonnyang.domain.cat.dto.response.CatDetailResponse;
import com.ogu.soonnyang.domain.cat.dto.response.CatListResponse;
import com.ogu.soonnyang.domain.cat.dto.request.CatRequest;
import com.ogu.soonnyang.domain.cat.service.CatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@Tag(name = "Cats", description = "Cats 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cats")
public class CatController {
    private final Logger LOGGER = LoggerFactory.getLogger(CatController.class);
    private final CatService catService;

    // 고양이 등록
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> insertCat(
            @RequestPart(value = "cat") @Validated CatRequest catRequest,
            @RequestPart(value = "file", required = false) MultipartFile image) {
        LOGGER.info("=========================insertCat===================================");
        Long memberId = 1L;
        final Long catId = catService.save(memberId, catRequest, image);
        return ResponseEntity.created(URI.create("/v1/cats/" + catId)).build();
    }

    // 고양이 리스트 조회

    @GetMapping
    public ResponseEntity<List<CatListResponse>> getCats() {
        Long memberId = 1L;
        List<CatListResponse> catListResponses = catService.getCats(memberId);
        return ResponseEntity.ok().body(catListResponses);
    }

    // 특정 고양이 상세 조회
    @Operation(operationId = "CatDetails", summary = "고양이 상세 조회", description = "고양이 정보를 상세 조회한다.")
    @GetMapping("/{catId}")
    public ResponseEntity<CatDetailResponse> getCat(@PathVariable("catId") Long catId) {
        CatDetailResponse catDetailResp = catService.getCat(catId);
        return ResponseEntity.ok().body(catDetailResp);
    }

    // 특정 고양이 수정
    @PutMapping("/{catId}")
    public ResponseEntity<MessageDTO> updateCat(
            @PathVariable("catId") Long catId,
            @RequestPart(value = "cat") @Validated CatRequest catRequest,
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