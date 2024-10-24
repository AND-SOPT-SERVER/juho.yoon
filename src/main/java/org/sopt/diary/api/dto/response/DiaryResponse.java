package org.sopt.diary.api.dto.response;

import org.sopt.diary.domain.Diary;

public record DiaryResponse(long id, String title) {

    public DiaryResponse(Diary diary) {
        this(diary.getId(), diary.getTitle());
    }
}
