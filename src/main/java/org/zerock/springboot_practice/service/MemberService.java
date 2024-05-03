package org.zerock.springboot_practice.service;

import org.zerock.springboot_practice.dto.MemberDTO;

public interface MemberService {
    void register(MemberDTO memberDTO);
    MemberDTO login1(String memberID, String memberPw);
    void login(MemberDTO memberDTO);
}
