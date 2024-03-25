package com.ogu.soonnyang.domain.cat.service;

import com.ogu.soonnyang.domain.cat.dto.CreateCatRequest;
import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.entity.type.CatState;
import com.ogu.soonnyang.domain.cat.repository.CatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatFactory {

    private final CatRepository catRepository;

    @Transactional
    public Cat save(CreateCatRequest request, String imageUrl) {

        Cat cat = Cat.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .followerCnt(0L)
                .imageUrl(imageUrl)
                .isActive(CatState.ACTIVE)
                .build();

        catRepository.save(cat);

        return cat;
    }
}
