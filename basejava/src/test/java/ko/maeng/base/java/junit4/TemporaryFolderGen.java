package ko.maeng.base.java.junit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

public class TemporaryFolderGen {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testCopy() throws Exception {
        File autoGenFile = folder.newFile("AutoGen.xml");
        File autoGenFolder = folder.newFolder("temp");
    }
}
