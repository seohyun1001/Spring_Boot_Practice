package org.zerock.springboot_practice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springboot_practice.dto.ProgramDTO;
import org.zerock.springboot_practice.repository.ProgramRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class ProgramServiceImpl implements ProgramService{
    private final ProgramRepository programRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProgramDTO> selectAll() {
        return programRepository.findAll().stream()
                .map(program -> modelMapper.map(program,ProgramDTO.class))
                .collect(Collectors.toList());
    }
}
