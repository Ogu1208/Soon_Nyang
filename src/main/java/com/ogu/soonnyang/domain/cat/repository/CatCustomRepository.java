package com.ogu.soonnyang.domain.cat.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatCustomRepository {
    List<CatListResp> getCats(Long id);
}
