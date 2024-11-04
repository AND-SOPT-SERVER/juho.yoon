package org.sopt.diary.diaries.repository;

import java.util.List;
import org.sopt.diary.diaries.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findTop10ByOrderByCreatedAtDesc();
}
