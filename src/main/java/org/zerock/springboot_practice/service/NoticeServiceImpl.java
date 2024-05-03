package org.zerock.springboot_practice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.springboot_practice.domain.Notice;
import org.zerock.springboot_practice.dto.NoticeDTO;
import org.zerock.springboot_practice.dto.PageRequestDTO;
import org.zerock.springboot_practice.dto.PageResponseDTO;
import org.zerock.springboot_practice.repository.NoticeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {
    private final ModelMapper modelMapper;
    private final NoticeRepository noticeRepository;

    @Override
    public Long register(NoticeDTO noticeDTO) {
        Notice notice = modelMapper.map(noticeDTO, Notice.class);
        Long no = noticeRepository.save(notice).getNo();
        return no;
    }

    @Override
    public NoticeDTO readOne(Long no) {
        Optional<Notice> result = noticeRepository.findById(no);
        Notice notice = result.orElseThrow();
        NoticeDTO noticeDTO = modelMapper.map(notice, NoticeDTO.class);
        return noticeDTO;
    }

    @Override
    public void modify(NoticeDTO dto) {
        Optional<Notice> result = noticeRepository.findById(dto.getNo());
        Notice vo = result.orElseThrow();
        vo.change(dto.getTitle(), dto.getContent());
        noticeRepository.save(vo);
    }

    @Override
    public void remove(Long no) {
        noticeRepository.deleteById(no);
    }

    @Override
    public PageResponseDTO<NoticeDTO> list(PageRequestDTO pageRequestDTO) {
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Notice> result = noticeRepository.searchAll(keyword, pageable);

        List<NoticeDTO> dtoList = result.stream().map(notice -> modelMapper.map(notice, NoticeDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<NoticeDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
