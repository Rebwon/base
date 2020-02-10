package ko.maeng.base.java8.java8inaction.chap8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RefactorLambda {
    public static void main(String[] args) throws IOException {
        String oneLine = processFile((BufferedReader b) -> b.readLine());
        String twoLine = processFile((BufferedReader b) -> b.readLine() + b.readLine());

    }

    private static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(
                new FileReader("src/main/resources/fp/war-and-peace.txt"))){
            return p.process(br);
        }
    }

    public interface BufferedReaderProcessor{
        String process(BufferedReader b) throws IOException;
    }
}
