package org.zerock.springboot_practice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springboot_practice.domain.Member;
import org.zerock.springboot_practice.dto.MemberDTO;
import org.zerock.springboot_practice.repository.MemberRepository;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @Override
    public void register(MemberDTO memberDTO){
        memberRepository.save(modelMapper.map(memberDTO, Member.class));
    }

    @Override
    public MemberDTO login1(String member_id, String member_pw) {
        Member member = memberRepository.findByIdAndPw(member_id, member_pw);
        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public void login(MemberDTO memberDTO) {
        memberRepository.findById(memberDTO.getMember_id());
    }
}
