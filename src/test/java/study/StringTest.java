package study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    @DisplayName("콤마(,) 구분되어 있는 문자열을 split 했을 때 콤마(,)를 기준으로 구분이 된다")
    void split_1() {
        // given
        String str = "1,2";

        // when
        String[] result = str.split(",");

        // then
        Assertions.assertEquals(Arrays.asList(result).size(), 2);
        Assertions.assertTrue(Arrays.asList(result).contains("1"));
        Assertions.assertTrue(Arrays.asList(result).contains("2"));
    }

    @Test
    @DisplayName("콤마(,) 구분되어 있지 않은 문자열을 split 했을 때 문자열 자체가 반환된다")
    void split_2() {
        // given
        String str = "1";

        // when
        String[] result = str.split(",");

        // then
        Assertions.assertEquals(Arrays.asList(result).size(), 1);
        Assertions.assertTrue(Arrays.asList(result).contains("1"));
    }

    @Test
    @DisplayName("substring() 으로 첫번째 문자와 마지막 문자를 제거할 수 있다")
    void substring() {
        // given
        String str = "(1,2)";

        // when
        String result = str.substring(1, str.length() - 1);

        // then
        Assertions.assertEquals(result, "1,2");
    }

    @Test
    @DisplayName("charAt()를 통해 특정 위치의 문자를 가져올 수 있다")
    void charAt_1() {
        // given
        String str = "abc";

        // when
        char ch = str.charAt(1);

        // then
        Assertions.assertEquals(ch, 'b');
    }

    @Test
    @DisplayName("charAt()를 통해 특정 위치의 문자를 가져올 때 범위를 벗어나면 에러를 반환한다")
    void charAt_2() {
        // given
        String str = "abc";

        // when & then
        Assertions.assertThrows(StringIndexOutOfBoundsException.class, () -> str.charAt(9));
    }
}
