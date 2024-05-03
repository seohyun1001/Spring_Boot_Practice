package org.zerock.springboot_practice.service;

import org.zerock.springboot_practice.dto.NoticeDTO;
import org.zerock.springboot_practice.dto.PageRequestDTO;
import org.zerock.springboot_practice.dto.PageResponseDTO;

import java.util.List;

public interface NoticeService {
    Long register(NoticeDTO noticeDTO);
    NoticeDTO readOne(Long no);
    void modify(NoticeDTO noticeDTO);
    void remove(Long no);
    PageResponseDTO<NoticeDTO> list(PageRequestDTO pageRequestDTO);
}
