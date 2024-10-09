package org.sopt.week1;

import java.util.List;
import org.sopt.week1.Main.UI.InvalidInputException;

public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    // APIS
    final List<Diary> getList() {
        return diaryService.findAll();
    }

    final void post(final String body) {
        diaryService.save(body);
    }

    final void delete(final String id) {
        diaryService.delete(parsedId(id));
    }

    final void patch(final String id, final String body) {
        diaryService.update(parsedId(id), body);
    }

    private long parsedId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}
