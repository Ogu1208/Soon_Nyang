package com.ogu.soonnyang.domain.cat.repository;

import com.ogu.soonnyang.domain.cat.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {

}