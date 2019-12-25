package ko.maeng.base.refactoring;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTester extends TestCase {
    private FileReader _input;

    public static void main(String[] args) {
        TestRunner.run(suite());
    }

    public FileReaderTester(String name){
        super(name);
    }

    protected void setUp(){
        try{
            _input = new FileReader("data.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("테스트 파일을 열 수 없음");
        }
    }

    protected void teatDown() {
        try{
            _input.close();
        } catch (IOException e) {
            throw new RuntimeException("테스트 파일을 닫는 중 에러 발생");
        }
    }

    public void testRead() throws IOException {
        char ch = '&';
        for(int i=0; i<4; i++)
            ch = (char) _input.read();
        assert('d' == ch);
    }

    public static Test suite(){
        TestSuite suite = new TestSuite();
        suite.addTest(new FileReaderTester("testRead"));
        return suite;
    }
}
