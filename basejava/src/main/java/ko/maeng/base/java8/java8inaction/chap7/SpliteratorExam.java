package ko.maeng.base.java8.java8inaction.chap7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorExam {
    public static void main(String[] args) {
        final String SENTENCE = " NEL   mezzo del cammin    di nostra   vita" +
                                "mi   ritrovai  in una   selva oscura" +
                                " ch   la   dritta via era  smarrita ";
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream) + " words");
    }

    public static int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                                        WordCounter::accmulate,
                                        WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for(char c : s.toCharArray()){
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }else{
                if(lastSpace){
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }
}
