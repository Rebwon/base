package ko.maeng.base.java;

import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void equals(){
        String value = "testVal";
        assertThat(value.equals("testVal")).isTrue();
    }

    @Test
    public void contentEquals(){
        // equals와 다른점은 객체를 비교하는게 아닌, Char형이나 StringBuffer,String 타입의
        // 입력값의 문자 순서를 비교함.
        String value = "testVal";
        assertThat(value.contentEquals(value)).isTrue();
    }

    @Test
    public void equalsIgnoreCase(){
        // 대소문자 구별 안하고 String 비교함.
        String value = "testVal";
        assertThat(value.equalsIgnoreCase("testval")).isTrue();
    }

    @Test
    public void compareTo(){
        // compareTo(String value) return int
        // 문자열의 길이를 비교한다.
        // 같을경우 0 아닐경우 1
        String value = "testVal";
        assertThat(value.compareTo("testVa")).isEqualTo(1);
        assertThat(value.compareTo("testVal")).isEqualTo(0);
    }

    @Test(expected = NullPointerException.class)
    public void compareToIgnoreCase(){
        // compareToIgnoreCase(String value) return int
        // 문자열의 길이를 비교한다. (대소문자 구별 안함)
        // 같을 경우 0 아닐경우 1
        // null을 넣을경우 NullPointerException 발생.
        String value = "testVal";
        assertThat(value.compareToIgnoreCase("testval")).isEqualTo(0);
        assertThat(value.compareToIgnoreCase("testva")).isEqualTo(1);
        value.compareToIgnoreCase(null);
    }

    @Test
    public void regionMatches() {
        // regionMatches(참조문자열시작인덱스, 비교할문자열, 비교할문자열의 시작인덱스, 끝인덱스)
        String value = "weather is sunny";
        assertThat(value.regionMatches(0, "weather", 0, 6)).isTrue();
        assertThat(value.regionMatches(11, "sunny", 0, 4)).isTrue();
    }

    @Test
    public void startsWith() {
        String value = "weather is sunny";
        assertThat(value.startsWith("weather", 0)).isTrue();
        assertThat(value.startsWith("weather", 1)).isFalse();
        assertThat(value.startsWith("sunny", 11)).isTrue();
        assertThat(value.startsWith("sunny", 10)).isFalse();
    }

    @Test
    public void endsWith() {
        String value = "weather is sunny";
        assertThat(value.endsWith("sunny")).isTrue();
        assertThat(value.endsWith("unny")).isTrue();
        assertThat(value.endsWith("nny")).isTrue();
        assertThat(value.endsWith("ny")).isTrue();
        assertThat(value.endsWith("y")).isTrue();
        assertThat(value.endsWith("is")).isFalse();
    }

    @Test
    public void indexOf(){
        // indexOf(String) return int
        // String의 인덱스의 위치를 반환한다.
        // indexOf(String, int) return int
        // String의 인덱스 위치를 반환하되, 찾고자하는 문자열이 중복이라면,
        // 무조건 다음 중복 문자의 위치를 반환.
        String value = "indexOf";
        assertThat(value.indexOf("i")).isEqualTo(0);
        assertThat(value.indexOf("d")).isEqualTo(2);
        assertThat(value.indexOf("x")).isEqualTo(4);
        assertThat(value.indexOf("i", 1)).isEqualTo(-1);
        value = "indexOf indexOf";
        assertThat(value.indexOf("i", 1)).isEqualTo(8);
    }
}