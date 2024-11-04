package org.sopt.diary.member.api.dto;

import org.sopt.diary.member.domain.Member;

public record LoginResponse(long memberId) {

    public LoginResponse(Member member) {
        this(member.getId());
    }
}
