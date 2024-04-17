package com.ogu.soonnyang.domain.post.repository;

import com.ogu.soonnyang.domain.cat.entity.QCat;
import com.ogu.soonnyang.domain.member.entity.QMember;
import com.ogu.soonnyang.domain.post.dto.PostResponse;
import com.ogu.soonnyang.domain.post.dto.QPostResponse;
import com.ogu.soonnyang.domain.post.entity.QPost;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QPost qPost = QPost.post;
    QCat qCat = QCat.cat;
    QMember qMember = QMember.member;

    @Override
    public Page<PostResponse> findAllNotDeleted(Pageable pageable) {
        List<PostResponse> content = jpaQueryFactory
                .select(new QPostResponse(qPost, qCat, qMember))
                .from(qPost)
                .leftJoin(qPost.cat, qCat)
                .leftJoin(qPost.member, qMember)
                .orderBy(qPost.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> totalCount = jpaQueryFactory
                .select(qPost.count())
                .from(qPost);

        return PageableExecutionUtils.getPage(content, pageable, totalCount::fetchOne);
    }
}
