package org.zerock.springboot_practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.springboot_practice.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeSearch {
}
