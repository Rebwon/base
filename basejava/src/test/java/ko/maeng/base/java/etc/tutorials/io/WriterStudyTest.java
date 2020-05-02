package ko.maeng.base.java.etc.tutorials.io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class WriterStudyTest {
    private WriterStudy writerStudy;
    private String fileName = "src/test/resources/io/test.txt";

    @Before
    public void setup() {
        writerStudy = new WriterStudy();
    }

    @Test
    public void writeBufferedWriter() throws IOException {
        writerStudy.writeStringUsingBufferedWritter();
    }

    @Test
    public void writeAppendStringBufferedWriter() throws IOException {
        writerStudy.writeStringAppendingUsingBufferedWriter();
    }

    @Test
    public void writeStringToFilePrintWriter() throws IOException {
        PrintWriter printWriter = new PrintWriter(
                new FileWriter(fileName));
        printWriter.println("Some String");
        printWriter.printf("Product name is %s and its price is %d $", "iPhone", 10000);
        printWriter.close();
    }

    @Test
    public void writePositionInFile() throws IOException {
        int data1 = 2014;

        writerStudy.writeToPosition(fileName, data1, 4);
        assertThat(data1).isEqualTo(writerStudy.readFromPosition(fileName, 4));
    }

    @Test
    public void writeToFileChannel() throws IOException {
        RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
        FileChannel channel = stream.getChannel();

        String value = "Hello";
        byte[] valueBytes = value.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(valueBytes.length);
        buffer.put(valueBytes);
        buffer.flip();

        channel.write(buffer);
        stream.close();
        channel.close();

        RandomAccessFile reader = new RandomAccessFile(fileName, "r");
        assertThat(value).isEqualTo(reader.readLine());
        reader.close();
    }

    @Test
    public void useJava7Files() throws IOException {
        String str = "Hello";

        Path path = Paths.get(fileName);
        byte[] strBytes = str.getBytes();

        Files.write(path, strBytes);

        String read = Files.readAllLines(path).get(0);
        assertThat(str).isEqualTo(read);
    }

    @Test
    public void writeToTempFile() throws IOException {
        String toWrite = "Hello";
        File tmpFile = File.createTempFile("test", ".temp");
        FileWriter writer = new FileWriter(tmpFile);
        writer.write(toWrite);
        writer.close();

        BufferedReader reader = new BufferedReader(new FileReader(tmpFile));
        assertThat(toWrite).isEqualTo(reader.readLine());
        reader.close();
    }

    @Test
    public void writeToLockFile() throws IOException {
        RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
        FileChannel channel = stream.getChannel();

        FileLock lock = null;
        try {
            lock = channel.tryLock();
        } catch (final OverlappingFileLockException e) {
            stream.close();
            channel.close();
        }
        stream.writeChars("test lock");
        lock.release();

        stream.close();
        channel.close();
    }
}