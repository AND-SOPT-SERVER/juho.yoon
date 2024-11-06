package org.sopt.diary.diaries.api.dto.response;

import java.time.LocalDate;
import org.sopt.diary.diaries.domain.Category;
import org.sopt.diary.diaries.domain.Diary;

public record DiaryDetailResponse(long id, String title, String content, Category category, LocalDate createdDate) {

    public DiaryDetailResponse(Diary diary) {
        this(diary.getId(), diary.getTitle(), diary.getContent(), diary.getCategory(), diary.getCreatedAt().toLocalDate());
    }
}
