package com.ogu.soonnyang.domain.cat.repository;

import com.ogu.soonnyang.domain.cat.dto.CatListResponse;
import com.querydsl.core.types.dsl.NumberExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CatCustomRepositoryImpl implements CatCustomRepository{
    /*
    @Override
    public List<CatListResponse> getCats(Long memberId) {
        NumberExpression<Long> isFollowing = follow.member.memberId.when(memberId).then(1L).otherwise(0L);
        return query
                .select(new QCatListResp(cat, isFollowing))
                .from(cat)
                .leftJoin(follow)
                .on(cat.catId.eq(follow.cat.catId), follow.member.memberId.eq(memberId))
                .where(cat.isActive.eq(1), cat.university.universityId.eq(universityId))
                .orderBy(isFollowing.desc(), cat.followerCnt.desc())
                .fetch();
    }
    */
}
