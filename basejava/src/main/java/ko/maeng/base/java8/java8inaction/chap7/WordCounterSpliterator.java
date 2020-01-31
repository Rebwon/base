package ko.maeng.base.java8.java8inaction.chap7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));    // 현재 문자를 소비한다.
        return currentChar < string.length();       // 소비할 문자가 남아 있으면 true를 반환한다.
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }
        for(int splitPos = currentSize/2 + currentChar; // 파싱할 문자열의 중간을 분할 위치로 설정한다.
            splitPos < string.length(); splitPos++){
            if(Character.isWhitespace(string.charAt(splitPos))){    // 다음 공백이 나올 때까지 분할 위치를 뒤로 이동시킨다.
                // 처음부터 분할 위치까지 문자열을 파싱할 새로운 Spliterator를 생성한다.
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos; // WordCounterSpliterator의 시작 위치를 분할 위치로 설정한다.
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
