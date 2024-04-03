package com.ogu.soonnyang.domain.cat.service;

import com.ogu.soonnyang.domain.cat.dto.CatRequest;
import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.entity.type.CatState;
import com.ogu.soonnyang.domain.cat.repository.CatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatFactory {

    private final CatRepository catRepository;

    @Transactional
    public Cat save(CatRequest request, String imageUrl) {

        log.info("request 의 TNRDate: " + request.getTNRDate());

        Cat cat = Cat.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .followerCnt(0L)
                .imageUrl(imageUrl)
                .isActive(CatState.ACTIVE)
                .TNRDate(request.getTNRDate())
                .build();

        catRepository.save(cat);

        log.info("cat 의 TNRDate: " + cat.getTNRDate());

        return cat;
    }
}
