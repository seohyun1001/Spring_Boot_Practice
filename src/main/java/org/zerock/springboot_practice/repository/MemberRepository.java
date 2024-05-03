package org.zerock.springboot_practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.springboot_practice.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query(value = "select member_id, member_pw, name, phone, Email1, Email2, gender, agree, create_date from member where member_id = ?1 and member_pw = ?2", nativeQuery = true)
    Member findByIdAndPw(String id, String pw);
}
