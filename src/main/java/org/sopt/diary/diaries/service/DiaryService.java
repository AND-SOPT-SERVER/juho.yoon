package org.sopt.diary.diaries.service;

import org.sopt.diary.diaries.api.dto.request.DiaryRequest;
import org.sopt.diary.diaries.api.dto.response.DiaryCratedResponse;
import org.sopt.diary.diaries.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.diaries.api.dto.response.DiaryListResponse;
import org.sopt.diary.diaries.api.dto.response.DiaryResponse;
import org.sopt.diary.diaries.domain.Category;
import org.sopt.diary.diaries.domain.Diary;
import org.sopt.diary.diaries.repository.DiaryRepository;
import org.sopt.diary.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public DiaryCratedResponse createDiary(Member member, DiaryRequest request) {
        validateDuplicationTitle(request);
        Diary diary = diaryRepository.save(request.toDiary(member));
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

    public DiaryListResponse getByCategory(Category category) {
        return new DiaryListResponse(diaryRepository.findTop10ByCategoryOrderByCreatedAtDesc(category)
                .stream()
                .map(DiaryResponse::new)
                .toList());
    }

    public void update(Member member, long diaryId, DiaryRequest diaryRequest) {
        Diary diary = getDiary(diaryId);
        validateOwner(member, diary);
        diary.update(diaryRequest.title(), diaryRequest.title());
    }

    public void deleteById(Member member, long diaryId) {
        Diary diary = getDiary(diaryId);
        validateOwner(member, diary);
        diaryRepository.deleteById(diaryId);
    }

    private void validateDuplicationTitle(DiaryRequest request) {
        boolean exists = diaryRepository.findAll().stream()
                .anyMatch(diary -> diary.getTitle().equals(request.title()));
        if (exists) {
            throw new IllegalArgumentException("중복된 제목 입니다.");
        }
    }

    private Diary getDiary(long diaryId) {
        return diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 다이어리 Id입니다."));
    }

    private void validateOwner(Member member, Diary diary) {
        if (!diary.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("해당 일기에 접근할 권한이 없습니다.");
        }
    }
}
