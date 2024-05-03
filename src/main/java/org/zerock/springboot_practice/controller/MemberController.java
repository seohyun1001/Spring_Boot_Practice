package org.zerock.springboot_practice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springboot_practice.domain.Member;
import org.zerock.springboot_practice.dto.MemberDTO;
import org.zerock.springboot_practice.repository.MemberRepository;
import org.zerock.springboot_practice.service.MemberService;

import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/join")
    public String joinGet(MemberDTO memberDTO) {
        return "/ex/join";
    }

    @PostMapping("/join")
    public String joinPost(MemberDTO memberDTO) {
        memberDTO.setEmail1(memberDTO.getMember_id());
        memberService.register(memberDTO);
        return "redirect:/ex/index";
    }

    @GetMapping("/login")
    public String loginGet(MemberDTO memberDTO) {
        return "/ex/login";
    }

    @PostMapping("/login")
    public String loginPost(String member_id, String member_pw, HttpServletRequest req,
                            Model model, RedirectAttributes redirectAttributes) {

        try {
            MemberDTO loginInfo = memberService.login1(member_id, member_pw);
            HttpSession session = req.getSession(true);
            session.setAttribute("loginInfo", loginInfo);
            model.addAttribute("info", loginInfo);
            log.info(loginInfo);
            return "redirect:/ex/index";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "아이디와 비밀번호를 확인해 주십시오.");
            return "redirect:/member/login";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        // session을 변수로 설정했기 때문에 중복 사용 가능
        HttpSession session = req.getSession();
        session.removeAttribute("loginInfo");
        session.invalidate();

        // session을 만들지 않고 getSession의 결과물에 바로 메서드를 실행하는 방식 : 중복 사용 불가능
//        req.getSession().removeAttribute("loginInfo");
//        req.getSession().invalidate();
        return "redirect:/ex/index";
    }

    }

