package ko.maeng.base.java.etc.tutorials.io.filenotfoundexception;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileNotFoundExceptionUnitTest {
    private static final Logger log = LoggerFactory.getLogger(FileNotFoundExceptionUnitTest.class);

    private String filename = Double.toString(Math.random());

    @Test(expected = BusinessException.class)
    public void raiseBusinessException() throws IOException {
        try{
            readFailingFile();
        } catch (FileNotFoundException ex) {
            throw new BusinessException("BusinessException: necessary file was not present.");
        }
    }

    @Test
    public void createFile() throws IOException {
        try {
            readFailingFile();
        } catch (FileNotFoundException ex) {
            try {
                new File(filename).createNewFile();
                readFailingFile();
            } catch (IOException ioe) {
                throw new RuntimeException("BusinessException: even creation is not possible.");
            }
        }
    }

    @Test
    public void logError() throws IOException {
        try {
            readFailingFile();
        } catch (FileNotFoundException ex) {
            log.error("Optional file " + filename + " was not found.");
        }
    }

    private void readFailingFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        br.readLine();
        // no need to close file
    }

    private class BusinessException extends RuntimeException {
        BusinessException(String s) {
            super(s);
        }
    }
}
