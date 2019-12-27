package ko.maeng.base.javajigi.fp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = new String(Files.readAllBytes(Paths
                        .get("src/main/resources/fp/war-and-peace.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        long count = 0;
        for(String w : words){
            if(w.length() > 12) count++;
        }
        return count;
    }

    public static List<String> selectWords() throws IOException {
        String contents = new String(Files.readAllBytes(Paths
                .get("src/main/resources/fp/war-and-peace.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        Comparator<String> longest = new Comparator<String>() {
            @Override
            public int compare(String x, String y) {
                return (x.length() > y.length()) ? -1 : ((x.length() == y.length()) ? 0 : 1);
            }
        };

        return words.stream().sorted(longest)
                .limit(100)
                .collect(Collectors.toList());
    }

    public static void printLongestWordTop100() throws IOException{
        String contents = new String(Files.readAllBytes(Paths
                .get("src/main/resources/fp/war-and-peace.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        // TODO 이 부분에 구현한다.
        // 단어의 길이를 비교해서 긴 단어를 기준으로 오름차순 정렬을 한다.
        // return 값이 0이나 음수면 자리바꿈을 하지 않고, 양수면 자리 바꿈을 수행한다.
        Comparator<String> longest = (x, y) -> (x.length() > y.length()) ? -1 : ((x.length() == y.length()) ? 0 : 1);

        words.stream()
                .sorted(longest)
                .limit(100)
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream().map(x -> 2 * x).collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream().reduce(0, (x, y) -> x + y);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream().filter(x -> x > 3).map((x) -> x * 2).reduce(0, (x,y) -> x + y);
    }
}
