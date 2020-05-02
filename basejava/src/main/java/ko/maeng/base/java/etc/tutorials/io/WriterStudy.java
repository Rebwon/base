package ko.maeng.base.java.etc.tutorials.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriterStudy {
    private String fileName = "src/test/resources/io/bufferedWriter.txt";

    public void writeStringUsingBufferedWritter() throws IOException {
        String str = "Hello";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(str);

        writer.close();
    }

    public void writeStringAppendingUsingBufferedWriter() throws IOException {
        String str = "World";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(' ');
        writer.append(str);

        writer.close();
    }

    public void writeToPosition(String fileName, int data, int position) throws IOException {
        RandomAccessFile writer = new RandomAccessFile(fileName, "rw");
        writer.seek(position);
        writer.writeInt(data);
        writer.close();
    }

    public int readFromPosition(String fileName, long position) throws IOException {
        int result = 0;
        RandomAccessFile reader = new RandomAccessFile(fileName, "r");
        reader.seek(position);
        result = reader.readInt();
        reader.close();
        return result;
    }

}
