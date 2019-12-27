package ko.maeng.base.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {

    @Test
    public void constructString(){
        String val = new String();  // 생성자 기본은 "" null값은 없음.
        assertThat(val).isEqualTo("");

        int[] ch16 = {0x0041}; // A
        String st16 = new String(ch16, 0, 1);
        assertThat(st16.length()).isEqualTo(1);
        assertThat(st16).isEqualTo("A");
    }

    @Test
    public void camelString(){
        String val = new String("testValue");
        assertThat(val).isEqualTo("testValue");
    }

    @Test
    public void length(){
        String val = "testValue";
        assertThat(val.length()).isEqualTo(9);
    }

    @Test
    public void split(){
        String[] val = "1".split(",");
        assertThat(val).contains("1");

        val = "1,2".split(",");
        assertThat(val).contains("1","2");
    }

    @Test
    public void isEmpty(){
        String val = "123";
        assertThat(val.isEmpty()).isFalse();
    }

    @Test
    public void charAt(){
        String val = "testValue12";
        // charAt은 String을 char형으로 변환하여 반환하는 메소드.
        // 따라서 테스트 시에 't'가 아니라 "t"를 입력하면 에러 발생.
        assertThat(val.charAt(0)).isEqualTo('t');
        assertThat(val.charAt(1)).isEqualTo('e');
        assertThat(val.charAt(2)).isEqualTo('s');
        assertThat(val.charAt(3)).isEqualTo('t');
        assertThat(val.charAt(4)).isEqualTo('V');
        assertThat(val.charAt(5)).isEqualTo('a');
        assertThat(val.charAt(6)).isEqualTo('l');
        assertThat(val.charAt(7)).isEqualTo('u');
        assertThat(val.charAt(8)).isEqualTo('e');
        assertThat(val.charAt(9)).isEqualTo('1');
        assertThat(val.charAt(10)).isEqualTo('2');
    }

    @Test
    public void codePointAt(){
        // codePointAt 메소드는 매개변수로 String의 인덱스를 입력하면
        // 반환값으로 unicode값을 반환한다.
        String val = "tes123";
        assertThat(val.codePointAt(0)).isEqualTo(116);
        assertThat(val.codePointAt(1)).isEqualTo(101);
        assertThat(val.codePointAt(2)).isEqualTo(115);
        assertThat(val.codePointAt(3)).isEqualTo(49);
        assertThat(val.codePointAt(4)).isEqualTo(50);
        assertThat(val.codePointAt(5)).isEqualTo(51);

        String val2 = "t";
        assertThat(val2.codePointAt(0)).isEqualTo(val.codePointAt(0));
    }

    @Test
    public void codePointBefore() {
        // codePointBefore 메소드는 매개변수로 넣은 값에서 -1을 한
        // 인덱스의 유니코드를 반환.
        String val = "tes123";
        assertThat(val.codePointBefore(1)).isEqualTo(116);
    }

    @Test
    public void codePointCount() {
        // codePointCount 메소드는 시작 인덱스와 끝 인덱스를 넣어주면,
        // 시작과 끝 사이의 인덱스 개수를 알려준다.
        String val = "tes123";
        assertThat(val.codePointCount(0, 0)).isEqualTo(0);
        assertThat(val.codePointCount(0, 1)).isEqualTo(1);
        assertThat(val.codePointCount(0, 2)).isEqualTo(2);
    }

    @Test
    public void offsetByCodePoints() {
        String val = "tes123";
        assertThat(val.offsetByCodePoints(0, 4)).isEqualTo(4);
        assertThat(val.offsetByCodePoints(0, 0)).isEqualTo(0);
        assertThat(val.offsetByCodePoints(0, 1)).isEqualTo(1);
    }

    @Test
    public void getChars(){
        char[] chars = new char[15];
        String str = "오늘 날씨는 정말 좋습니다.";
        assertThat(chars.length).isEqualTo(str.length());

        // getChars메소드는 String 객체가 가지고 있는 값을 char 배열에 복사하는 기능.
        // String str의 3에서부터 15앞까지 추출해서 chars의 0번째 위치부터 넣는다.
        str.getChars(3, 15, chars, 0);
        assertThat(Arrays.toString(chars)).contains("날");
    }

}