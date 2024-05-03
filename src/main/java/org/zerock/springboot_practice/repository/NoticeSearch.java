package org.zerock.springboot_practice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.springboot_practice.domain.Notice;

public interface NoticeSearch {
    Page<Notice> searchAll(String keyword, Pageable pageable);
}
