package com.ogu.soonnyang.domain.cat.controller;

import com.ogu.soonnyang.domain.cat.dto.CatInfoReq;
import com.ogu.soonnyang.domain.cat.service.CatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatController {
    private final Logger LOGGER = LoggerFactory.getLogger(CatController.class);
    private final CatService catService;

    @PostMapping("")
    public ResponseEntity<Void> insertCat(@RequestBody CatInfoReq catInfoReq) {
        LOGGER.info("=========================insertCat===================================");

        final long catId = catService.save(memberId, catInfoReq);
        return ResponseEntity.created(URI.create("/cats/" + catId)).build();
    }
}
