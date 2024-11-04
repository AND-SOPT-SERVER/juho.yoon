package org.sopt.member.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.sopt.member.domain.Member;

public record SignupRequest(
        @NotNull(message = "이름을 입력해주세요.")
        @Size(max = 10, message = "10자 이하의 이름으로 설정해주세요.")
        String username,
        @NotNull(message = "비밀번호를 입력해주세요.")
        String password,
        @NotNull(message = "닉네임을 입력해주세요.")
        @Size(max = 10, message = "10자 이하의 닉네임으로 설정해주세요.")
        String nickname,
        @NotNull(message = "나이를 입력해주세요.")
        int age) {

    public Member toMember() {
        return new Member(username, password, nickname, age);
    }
}
