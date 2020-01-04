package ko.maeng.base.java.myjunit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Assert {
    private static final Logger logger = LoggerFactory.getLogger(Assert.class);

    private Assert() {} // 인스턴스 생성을 막기 위해 기본생성자 private 선언

    public static void assertTrue(boolean condition) {
        if(!condition) {
            throw new AssertionFailedError();
        }

        logger.info("Test Passed");
    }
}
