package org.zerock.springboot_practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.springboot_practice.service.ProgramService;

@Controller
@Log4j2
@RequestMapping("/program")
@RequiredArgsConstructor
public class ProgramController {
    @Autowired
    private ProgramService programService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("programList", programService.selectAll());
        return "/ex/program";
    }
}
