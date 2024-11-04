package org.sopt.diary.diaries.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.sopt.diary.diaries.domain.Diary;
import org.sopt.diary.member.domain.Member;

public record DiaryRequest(
        @NotBlank(message = "제목을 입력해주세요.")
        @Size(max = 30, message = "제목은 공백 포함 30자 이하로 입력해주세요.")
        String title,
        @NotBlank(message = "내용을 입력해주세요.")
        @Size(max = 100, message = "내용은 공백 포함 100자 이하로 입력해주세요.")
        String content) {

    public Diary toDiary(Member member) {
        return new Diary(title, content, member);
    }
}
