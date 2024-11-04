package org.sopt.diary.member.api;

import java.net.URI;
import jakarta.validation.Valid;
import org.sopt.diary.member.api.dto.LoginRequest;
import org.sopt.diary.member.api.dto.LoginResponse;
import org.sopt.diary.member.api.dto.SignupRequest;
import org.sopt.diary.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/luckybicky")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = memberService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signup(@RequestBody @Valid SignupRequest signupRequest) {
        long memberId = memberService.signup(signupRequest);
        return ResponseEntity.created(URI.create("luckybicky/signup/" + memberId)).build();
    }
}
