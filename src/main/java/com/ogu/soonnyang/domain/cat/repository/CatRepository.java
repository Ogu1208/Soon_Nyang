package com.ogu.soonnyang.domain.cat.repository;

import com.ogu.soonnyang.domain.cat.entity.Cat;
import com.ogu.soonnyang.domain.cat.entity.type.CatState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findCatByIsActive(CatState catState);
}