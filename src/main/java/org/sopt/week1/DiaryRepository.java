package org.sopt.week1;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    public void save(Diary diary) {
        long id = numbering.incrementAndGet();
        storage.put(id, diary.getBody());
    }

    public void delete(Diary diary) {
        storage.remove(diary.getId());
    }

    public void update(Diary diary) {
        storage.replace(diary.getId(), diary.getBody());
    }

    public List<Diary> findAll() {
        return storage.entrySet()
                .stream()
                .map(entry -> new Diary(entry.getKey(), entry.getValue()))
                .toList();
    }

    public Optional<Diary> findById(long id) {
        if (storage.containsKey(id)) {
            return Optional.of(new Diary(id, storage.get(id)));
        }
        return Optional.empty();
    }
}
