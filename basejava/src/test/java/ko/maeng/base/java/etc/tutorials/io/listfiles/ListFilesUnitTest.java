package ko.maeng.base.java.etc.tutorials.io.listfiles;

import ko.maeng.base.java.etc.tutorials.io.ListFiles;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ListFilesUnitTest {
    private ListFiles listFiles = new ListFiles();
    private String dir = "src/test/resources/listFilesUnitTestFolder";
    private static final int DEPTH = 1;
    private Set<String> EXPECTED_FILE_LIST = new HashSet<String>() {
        {
            add("test.xml");
            add("employee.json");
            add("students.json");
            add("country.txt");
        }
    };

    @Test
    public void givenDir_whenUsingJavaIO_thenListAllFiles() throws IOException {
        assertEquals(EXPECTED_FILE_LIST, listFiles.listFilesUsingJavaIO(dir));
    }

    @Test
    public void givenDir_whenWalkingTree_thenListAllFiles() throws IOException {
        assertEquals(EXPECTED_FILE_LIST, listFiles.listFilesUsingFileWalk(dir,DEPTH));
    }

    @Test
    public void givenDir_whenWalkingTreeWithVisitor_thenListAllFiles() throws IOException {
        assertEquals(EXPECTED_FILE_LIST, listFiles.listFilesUsingFileWalkAndVisitor(dir));
    }

    @Test
    public void givenDir_whenUsingDirectoryStream_thenListAllFiles() throws IOException {
        assertEquals(EXPECTED_FILE_LIST, listFiles.listFilesUsingDirectoryStream(dir));
    }
}
