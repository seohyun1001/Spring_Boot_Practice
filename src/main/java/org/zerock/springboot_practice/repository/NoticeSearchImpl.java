package org.zerock.springboot_practice.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.springboot_practice.domain.Notice;
import org.zerock.springboot_practice.domain.QNotice;

import java.util.List;

public class NoticeSearchImpl extends QuerydslRepositorySupport implements NoticeSearch {

    public NoticeSearchImpl() {
        super(Notice.class);
    }

    @Override
    public Page<Notice> searchAll(String keyword, Pageable pageable) {

        // queryDSL을 이용한 객체 생성
        QNotice notice = QNotice.notice;
        JPQLQuery<Notice> query = from(notice);
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // 검색 조건인 types와 keyword가 존재하는지 확인하는 if문
        if (keyword != null) {
            booleanBuilder.or(notice.title.contains(keyword));
            booleanBuilder.or(notice.content.contains(keyword));
            query.where(booleanBuilder);
        }

        query.where(notice.no.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);

        List<Notice> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
