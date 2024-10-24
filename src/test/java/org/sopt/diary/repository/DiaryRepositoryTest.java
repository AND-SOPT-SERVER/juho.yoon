package org.sopt.diary.repository;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.diary.domain.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class DiaryRepositoryTest {

    @Autowired
    private DiaryRepository diaryRepository;

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 15; i++) {
            diaryRepository.save(new Diary("Title " + i, "Content " + i));
        }
    }

    @DisplayName("10개의 일기를 조회할 수 있다.")
    @Test
    public void findTop10ByOrderByCreatedAtDesc() {
        List<Diary> latest10Diaries = diaryRepository.findTop10ByOrderByCreatedAtDesc();

        assertThat(latest10Diaries).hasSize(10);
        assertThat(latest10Diaries.get(0).getTitle()).isEqualTo("Title 15");
    }
}
