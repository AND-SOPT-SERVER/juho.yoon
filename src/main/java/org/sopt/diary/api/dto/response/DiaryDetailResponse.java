package org.sopt.diary.api.dto.response;

import java.time.LocalDate;
import org.sopt.diary.domain.Diary;

public record DiaryDetailResponse(long id, String title, String content, LocalDate createdDate) {

    public DiaryDetailResponse(Diary diary) {
        this(diary.getId(), diary.getTitle(), diary.getContent(), diary.getCreatedAt().toLocalDate());
    }
}
