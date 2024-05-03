package org.zerock.springboot_practice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    @Id
    private String member_id;

    private String member_pw;
    private String name;
    private String phone;
    private String email1;
    private String email2;
    private String gender;
    private boolean agree;

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LocalDate create_date;
}
