package org.sopt.week1;

import java.util.Objects;
import org.sopt.week1.Main.UI.InvalidInputException;

public class Diary {
    private static final int MAX_BODY_LENGTH = 30;

    private Long id;
    private final String body;

    public Diary(Long id, String body) {
        validateBodyLength(body);
        this.id = id;
        this.body = body;
    }

    public Diary(String body) {
        this(null, body);
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    private void validateBodyLength(String body) {
        if (body.length() > MAX_BODY_LENGTH) {
            throw new InvalidInputException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Diary diary = (Diary) o;
        return Objects.equals(id, diary.id) && Objects.equals(body, diary.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body);
    }
}
