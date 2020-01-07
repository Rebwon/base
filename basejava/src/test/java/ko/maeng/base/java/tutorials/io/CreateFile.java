package ko.maeng.base.java.tutorials.io;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

//import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateFile {

    @Ignore
    @Test
    public void withJava6() throws IOException {
        File newFile = new File("src/test/resources/newFile_jdk6.txt");
        boolean success = newFile.createNewFile();
        assertThat(success).isTrue();
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void withJava7() throws IOException {
        Path newFilePath = Paths.get("src/test/resources/newFile_jdk7.txt");
        Files.createFile(newFilePath);
    }

    @Ignore
    @Test
    public void withGuavaV18() throws IOException {
        //Files.touch(new File("src/test/resources/newFile_guava.txt"));
    }

    @Test
    public void withApacheCommonIO() throws IOException {
        FileUtils.touch(new File("src/test/resources/newFile_common.txt"));
    }
}
