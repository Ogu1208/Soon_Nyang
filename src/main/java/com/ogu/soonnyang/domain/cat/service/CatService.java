package com.ogu.soonnyang.domain.cat.service;

import com.ogu.soonnyang.domain.cat.dto.CatInfoReq;
import com.ogu.soonnyang.domain.cat.repository.CatCustomRepository;
import com.ogu.soonnyang.domain.cat.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatService {
    private final Logger LOGGER = LoggerFactory.getLogger(CatService.class);
    private final CatRepository catRepository;
    private final CatCustomRepository catCustomRepository;

    /*
    public Long save(Long memberId, CatInfoReq catInfoReq) {
    }
    */
}
