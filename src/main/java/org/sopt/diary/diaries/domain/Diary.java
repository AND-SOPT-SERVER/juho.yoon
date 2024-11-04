package org.sopt.diary.diaries.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.sopt.diary.config.domain.BaseEntity;
import org.sopt.diary.member.domain.Member;

@Entity
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String title;

    @Column(nullable = false, length = 30)
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    protected Diary() {
    }

    public Diary(Long id, String title, String content, Member member, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public Diary(String title, String content, Member member, Category category) {
        this(null, title, content, member, category);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Member getMember() {
        return member;
    }

    public Category getCategory() {
        return category;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
