package com.ogu.soonnyang.domain.member.repository;

import com.ogu.soonnyang.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(Long memberId);

    Member getByEmail(String email);

    Member findByEmail(String email);

    boolean existsByEmail(String email);

}
