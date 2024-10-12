package org.sopt.week1;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;
import org.sopt.week1.Main.UI.InvalidInputException;

public class Diary {
    private static Logger logger = Logger.getGlobal();
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
        int count = countGraphemes(body);

        if (count > MAX_BODY_LENGTH) {
            logger.warning("입력된 글자 수가 30자를 넘습니다. 길이 : " + count);
            throw new InvalidInputException();
        }
    }

    private int countGraphemes(String body) {
        BreakIterator breakIterator = BreakIterator.getCharacterInstance(Locale.ROOT);
        breakIterator.setText(body);

        int graphemeCount = 0;
        while (breakIterator.next() != BreakIterator.DONE) {
            graphemeCount++;
        }
        return graphemeCount;
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
