package org.sopt.week1;

import java.util.List;
import org.sopt.week1.Main.UI.InvalidInputException;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    public void save(String body) {
        Diary diary = new Diary(body);
        diaryRepository.save(diary);
    }

    public List<Diary> findAll() {
        return diaryRepository.findAll();
    }

    public void delete(long id) {
        diaryRepository.findById(id)
                .ifPresentOrElse(diaryRepository::delete,
                        () -> {
                            throw new InvalidInputException();
                        });
    }

    public void update(long id, String body) {
        diaryRepository.findById(id)
                .ifPresentOrElse(diary -> diaryRepository.update(new Diary(id, body)),
                        () -> {
                            throw new InvalidInputException();
                        });
    }
}
