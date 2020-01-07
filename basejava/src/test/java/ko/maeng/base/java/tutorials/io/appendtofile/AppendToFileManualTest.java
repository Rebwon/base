package ko.maeng.base.java.tutorials.io.appendtofile;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.assertj.core.api.Assertions.assertThat;

public class AppendToFileManualTest {
    public static final String filename = "src/test/resources/countries.properties";

    @Before
    @After
    public void setup() throws Exception{
        PrintWriter writer = new PrintWriter(filename);
        writer.print("UK\r\n" + "US\r\n" + "Germany\r\n");
        writer.close();
    }

    @Test
    public void appendToFileUsingGuava() throws IOException {
        File file = new File(filename);
        CharSink chs = com.google.common.io.Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND);
        chs.write("Spain\r\n");

        assertThat(StreamUtils.getStringFromInputStream(new FileInputStream(filename)))
                .isEqualTo("UK\r\n" + "US\r\n" + "Germany\r\n" + "Spain\r\n");
    }

    @Test
    public void appendToFileUsingJava() throws IOException {
        Files.write(Paths.get(filename), "Spain\r\n".getBytes(), StandardOpenOption.APPEND);

        assertThat(StreamUtils.getStringFromInputStream(new FileInputStream(filename)))
                .isEqualTo("UK\r\n" + "US\r\n" + "Germany\r\n" + "Spain\r\n");
    }

    @Test
    public void appendToFileUsingApacheCommon() throws IOException {
        File file = new File(filename);
        FileUtils.writeStringToFile(file, "Spain\r\n", StandardCharsets.UTF_8, true);

        assertThat(StreamUtils.getStringFromInputStream(new FileInputStream(filename)))
                .isEqualTo("UK\r\n" + "US\r\n" + "Germany\r\n" + "Spain\r\n");
    }

    @Test
    public void appendToFileUsingFileOutputStream() throws IOException {
        FileOutputStream fos = new FileOutputStream(filename, true);
        fos.write("Spain\r\n".getBytes());
        fos.close();

        assertThat(StreamUtils.getStringFromInputStream(new FileInputStream(filename)))
                .isEqualTo("UK\r\n" + "US\r\n" + "Germany\r\n" + "Spain\r\n");
    }

    @Test
    public void appendToFileUsingFileWriter() throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Spain");
        bw.newLine();
        bw.close();

        assertThat(StreamUtils.getStringFromInputStream(new FileInputStream(filename)))
                .isEqualTo("UK\r\n" + "US\r\n" + "Germany\r\n" + "Spain\r\n");
    }
}
