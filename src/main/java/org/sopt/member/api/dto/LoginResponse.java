package org.sopt.member.api.dto;

import org.sopt.member.domain.Member;

public record LoginResponse(long memberId) {

    public LoginResponse(Member member) {
        this(member.getId());
    }
}
