package com.ogu.soonnyang.domain.cat.repository;

import com.ogu.soonnyang.domain.cat.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, Long> {
    Optional<Cat> findCatByCatId(Long catId);

}