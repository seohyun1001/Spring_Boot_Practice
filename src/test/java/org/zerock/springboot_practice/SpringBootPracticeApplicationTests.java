package org.zerock.springboot_practice;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.springboot_practice.domain.Member;
import org.zerock.springboot_practice.repository.MemberRepository;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class SpringBootPracticeApplicationTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testInsert() {
        IntStream.range(1, 10).forEach(i -> {
            Member member = Member.builder()
                    .member_id("id0" + i)
                    .member_pw("pw0" + i)
                    .name("user0" + i)
                    .phone("0101234000" + i)
                    .build();
            Member result = memberRepository.save(member);
            log.info(result.getMember_id());

        });

    }

}
