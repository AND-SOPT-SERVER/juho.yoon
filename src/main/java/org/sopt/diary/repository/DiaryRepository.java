package org.sopt.diary.repository;

import java.util.List;
import org.sopt.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findTop10ByOrderByCreatedAtDesc();
}
