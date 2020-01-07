package ko.maeng.base.java.tutorials.io.directories;

import org.junit.Before;

import java.io.File;

public class NewDirectoryUnitTest {
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));

    @Before
    public void beforeEach() {
        File newDirectory = new File(TEMP_DIRECTORY, "new_directory");
        File nestedInNewDirectory = new File(newDirectory, "nested_directory");
        File existingDirectory = new File(TEMP_DIRECTORY, "existing_directory");
        File existingNestedDirectory = new File(existingDirectory, "existing_nested_directory");
        File nestedInExistingDirectory = new File(existingDirectory, "nested_directory");

        nestedInNewDirectory.delete();
        newDirectory.delete();
        nestedInExistingDirectory.delete();
        existingDirectory.mkdir();
        existingNestedDirectory.mkdir();
    }


}
