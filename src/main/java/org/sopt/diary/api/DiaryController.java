package org.sopt.diary.api;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.sopt.diary.api.dto.request.DiaryRequest;
import org.sopt.diary.api.dto.response.DiaryCratedResponse;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/luckybicky/diaries")
@Validated
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    ResponseEntity<DiaryCratedResponse> post(@Valid @RequestBody DiaryRequest request) {
        return ResponseEntity.ok(diaryService.createDiary(request));
    }

    @GetMapping
    ResponseEntity<DiaryListResponse> getAll() {
        return ResponseEntity.ok(diaryService.getAll());
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<DiaryDetailResponse> getById(
            @PathVariable @Min(value = 1L, message = "다이어리 식별자는 양수로 이루어져야 합니다.") long diaryId) {
        return ResponseEntity.ok(diaryService.getById(diaryId));
    }

    @PatchMapping("/{diaryId}")
    public ResponseEntity<Void> update(
            @PathVariable @Min(value = 1L, message = "다이어리 식별자는 양수로 이루어져야 합니다.") long diaryId,
            @Valid @RequestBody DiaryRequest request) {
        diaryService.update(diaryId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<Void> deleteById(
            @PathVariable @Min(value = 1L, message = "다이어리 식별자는 양수로 이루어져야 합니다.") long diaryId) {
        diaryService.deleteById(diaryId);
        return ResponseEntity.ok().build();
    }
}
