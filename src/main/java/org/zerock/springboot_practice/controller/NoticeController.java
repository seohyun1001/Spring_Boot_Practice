package org.zerock.springboot_practice.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springboot_practice.dto.NoticeDTO;
import org.zerock.springboot_practice.dto.PageRequestDTO;
import org.zerock.springboot_practice.service.NoticeService;

@Controller
@Log4j2
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/hello")
    public void hello(Model model) {
        log.info("hello......................");
        model.addAttribute("msg", "HELLO WORLD");
    }


    @GetMapping("/ex/index")
    public void index(Model model) {
    }

    @GetMapping("/ex/mypage")
    public void mypage(Model model) {
    }
    @GetMapping("/ex/notice_list")
    public void notice_list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("responseDTO", noticeService.list(pageRequestDTO));
    }
    @GetMapping("/ex/notice_add")
    public void notice_addGet(Model model) {
    }
    @PostMapping("/ex/notice_add")
    public String notice_addPost(NoticeDTO noticeDTO
            , RedirectAttributes redirectAttributes
            , Model model) {
        Long no = noticeService.register(noticeDTO);
        redirectAttributes.addFlashAttribute("no",no);
        return "redirect:/ex/notice_list";
    }
    @PostMapping("/ex/modify")
    public String notice_modify(PageRequestDTO pageRequestDTO,
                                @Valid NoticeDTO noticeDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            redirectAttributes.addAttribute("no",noticeDTO.getNo());
            return "redirect:/ex/notice_modify?no="+noticeDTO.getNo();
        }
        noticeService.modify(noticeDTO);
        return "redirect:/ex/notice_view?no="+noticeDTO.getNo();
    }
    @PostMapping("/ex/notice_remove")
    public String notice_remove(Long no,Model model) {
        noticeService.remove(no);
        return "redirect:/ex/notice_list";
    }
    @GetMapping({"/ex/notice_view","/ex/notice_modify"})
    public void notice_view(Long no,Model model) {
        NoticeDTO dto = noticeService.readOne(no);
        model.addAttribute("notice", dto) ;
    }
    @GetMapping("/ex/program")
    public void program(Model model) {
    }
}
