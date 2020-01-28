package ko.maeng.base.java8.java8inaction.chap5;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileStreamTest {
    @Test
    public void findWords(){
        long uniqueWords = 0;
        try(Stream<String> lines =
               Files.lines(Paths.get("src/main/resources/fp/war-and-peace.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e){

        }
        assertThat(uniqueWords).isEqualTo(41968L);
    }
}
