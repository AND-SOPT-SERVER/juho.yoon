package org.sopt.diary.service;

import org.sopt.diary.api.dto.request.DiaryRequest;
import org.sopt.diary.api.dto.response.DiaryCratedResponse;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.response.DiaryResponse;
import org.sopt.diary.domain.Diary;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public DiaryCratedResponse createDiary(DiaryRequest request) {
        Diary diary = diaryRepository.save(request.toDiary());
        return new DiaryCratedResponse(diary.getId());
    }

    public DiaryListResponse getAll() {
        return new DiaryListResponse(diaryRepository.findTop10ByOrderByCreatedAtDesc()
                .stream()
                .map(DiaryResponse::new)
                .toList());
    }

    public DiaryDetailResponse getById(long diaryId) {
        return new DiaryDetailResponse(getDiary(diaryId));
    }

    public void update(long diaryId, DiaryRequest diaryRequest) {
        Diary diary = getDiary(diaryId);
        diary.update(diaryRequest.title(), diaryRequest.title());
    }

    public void deleteById(long diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    private Diary getDiary(long diaryId) {
        return diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 다이어리 Id입니다."));
    }
}
