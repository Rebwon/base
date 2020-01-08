package ko.maeng.base.java.tutorials.io.writetofile;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class JavaWriteToFileUnitTest {
    private String fileName = "src/test/resources/test_write.txt";
    private String fileName1 = "src/test/resources/test_write_1.txt";
    private String fileName2 = "src/test/resources/test_write_2.txt";
    private String fileName3 = "src/test/resources/test_write_3.txt";
    private String fileName4 = "src/test/resources/test_write_4.txt";
    private String fileName5 = "src/test/resources/test_write_5.txt";

    @Test
    public void whenWriteStringUsingBufferedWritter_thenCorrect() throws IOException {
        final String str = "Hello";
        final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName3));
        writer.write(str);
        writer.close();
    }

    @Test
    public void whenAppendStringUsingBufferedWritter_thenOldContentShouldExistToo() throws IOException {
        final String str = "World";
        final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName3, true));
        writer.append(' ');
        writer.append(str);
        writer.close();
    }

    @Test
    public void givenWritingStringToFile_whenUsingPrintWriter_thenCorrect() throws IOException {
        final FileWriter fileWriter = new FileWriter(fileName);
        final PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Some String");
        printWriter.printf("Product name is %s and its price is %d $", "iPhone", 10002);
        printWriter.close();
    }

    @Test
    public void givenWritingStringToFile_whenUsingFileOutputStream_thenCorrect() throws IOException {
        final String str = "Hello FileOutputStream!";
        final FileOutputStream outputStream = new FileOutputStream(fileName3);
        final byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    @Test
    public void givenWritingToFile_whenUsingDataOutputStream_thenCorrect() throws IOException {
        final String value = "Hello";
        final FileOutputStream fos = new FileOutputStream(fileName1);
        final DataOutputStream outputStream = new DataOutputStream(fos);
        outputStream.writeUTF(value);
        outputStream.close();

        String result;
        final FileInputStream fis = new FileInputStream(fileName1);
        final DataInputStream inputStream = new DataInputStream(fis);
        result = inputStream.readUTF();
        inputStream.close();

        assertThat(value).isEqualTo(result);
    }

    @Test
    public void whenWritingToSpecificPositionInFile_thenCorrect() throws IOException {
        final int data1 = 2014;
        final int data2 = 1500;
        writeToPosition(fileName2, data1, 4);
        assertThat(data1).isEqualTo(readFromPosition(fileName2, 4));
        writeToPosition(fileName2, data2, 4);
        assertThat(data2).isEqualTo(readFromPosition(fileName2, 4));
    }

    @Test
    public void whenTryToLockFile_thenItShouldBeLocked() throws IOException {
        final RandomAccessFile stream = new RandomAccessFile(fileName4, "rw");
        final FileChannel channel = stream.getChannel();

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

    @Test
    public void givenWritingToFile_whenUsingFileChannel_thenCorrect() throws IOException {
        final RandomAccessFile stream = new RandomAccessFile(fileName5, "rw");
        final FileChannel channel = stream.getChannel();
        final String value = "Hello";
        final byte[] strBytes = value.getBytes();
        final ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
        buffer.put(strBytes);
        buffer.flip();
        channel.write(buffer);
        stream.close();
        channel.close();

        final RandomAccessFile reader = new RandomAccessFile(fileName5, "r");
        assertEquals(value, reader.readLine());
        reader.close();
    }

    @Test
    public void whenWriteToTmpFile_thenCorrect() throws IOException {
        final String toWrite = "Hello";
        final File tmpFile = File.createTempFile("test", ".tmp");
        final FileWriter writer = new FileWriter(tmpFile);
        writer.write(toWrite);
        writer.close();

        final BufferedReader reader = new BufferedReader(new FileReader(tmpFile));
        assertEquals(toWrite, reader.readLine());
        reader.close();
    }

    @Test
    public void givenUsingJava7_whenWritingToFile_thenCorrect() throws IOException {
        final String str = "Hello";

        final Path path = Paths.get(fileName3);
        final byte[] strToBytes = str.getBytes();

        Files.write(path, strToBytes);

        final String read = Files.readAllLines(path, Charset.defaultCharset()).get(0);
        assertEquals(str, read);
    }

    private void writeToPosition(final String filename, final int data, final long position) throws IOException {
        final RandomAccessFile writer = new RandomAccessFile(filename, "rw");
        writer.seek(position);
        writer.writeInt(data);
        writer.close();
    }

    private int readFromPosition(final String filename, final long position) throws IOException {
        int result = 0;
        final RandomAccessFile reader = new RandomAccessFile(filename, "r");
        reader.seek(position);
        result = reader.readInt();
        reader.close();
        return result;
    }
}
