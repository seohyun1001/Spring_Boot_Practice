package org.zerock.springboot_practice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    @NotEmpty
    private String member_id;
    @NotEmpty
    private String member_pw;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;
    private String email1;
    private String email2;
    private String gender;
    private boolean agree;
    private LocalDate create_date;

}
