package org.sopt.diary.member.service;

import org.sopt.diary.member.api.dto.LoginRequest;
import org.sopt.diary.member.api.dto.LoginResponse;
import org.sopt.diary.member.api.dto.SignupRequest;
import org.sopt.diary.member.domain.Member;
import org.sopt.diary.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Member saved = memberRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new IllegalArgumentException("userName 해당하는 회원이 존재하지 않습니다."));
        saved.verifyPassword(loginRequest.password());
        return new LoginResponse(saved);
    }

    public long signup(SignupRequest signupRequest) {
        Member saved = memberRepository.save(signupRequest.toMember());
        return saved.getId();
    }

    public Member findByMemberId(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원의 Id입니다."));
    }
}
