package ko.maeng.base.java.tutorials.io;

import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class OutPutTest {
    @Test
    public void fileOutPutStream() throws IOException {
        // String을 이진 데이터로 변환하여 파일에 쓴다.
        String str = "Hello World";
        FileOutputStream outputStream = new FileOutputStream("src/test/resources/io/test.txt");
        byte[] strBytes = str.getBytes();
        outputStream.write(strBytes);

        outputStream.close();
    }

    @Test
    public void dataOutPutStream() throws IOException {
        // given
        String value = "Hello";
        FileOutputStream fos = new FileOutputStream("src/test/resources/io/test.txt");
        DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

        // when
        outStream.writeUTF(value);
        outStream.close();

        // then
        String result;
        FileInputStream fis = new FileInputStream("src/test/resources/io/test.txt");
        DataInputStream reader = new DataInputStream(fis);
        result = reader.readUTF();
        reader.close();

        assertThat(value).isEqualTo(result);
    }
}
