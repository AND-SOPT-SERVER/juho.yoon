package org.sopt.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Integer age;

    protected Member() {
    }

    public Member(Long id, String username, String password, String nickname, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.age = age;
    }

    public Member(String username, String password, String nickname, Integer age) {
        this(null, username, password, nickname, age);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void verifyPassword(String password) {
        if (!password.equals(this.password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
