package com.ogu.soonnyang.domain.member.repository;

import com.ogu.soonnyang.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> getByEmail(String email);

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
