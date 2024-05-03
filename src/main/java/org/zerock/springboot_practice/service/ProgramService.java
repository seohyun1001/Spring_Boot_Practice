package org.zerock.springboot_practice.service;

import org.zerock.springboot_practice.dto.ProgramDTO;

import java.util.List;

public interface ProgramService {
    List<ProgramDTO> selectAll();
}
