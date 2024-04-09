import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("두 정수를 더하면 두 정수를 더한 값이 반환된다")
    void plus() {
        // when
        int result = calculator.calculate("1+2");
        // then
        Assertions.assertEquals(result, 3);
    }

    @Test
    @DisplayName("두 정수를 빼면 두 정수를 뺀 값이 반환된다")
    void minus() {
        // when
        int result = calculator.calculate("1-2");
        // then
        Assertions.assertEquals(result, -1);
    }

    @Test
    @DisplayName("두 정수를 곱하면 두 정수를 뺀 값이 반환된다")
    void multiply() {
        // when
        int result = calculator.calculate("1*2");
        // then
        Assertions.assertEquals(result, 2);
    }

    @Test
    @DisplayName("두 정수를 곱하면 두 정수를 뺀 값이 반환된다")
    void divide() {
        // when
        int result = calculator.calculate("4/2");
        // then
        Assertions.assertEquals(result, 2);
    }

    @Test
    @DisplayName("아무 계산하지 않으면 결과는 0이다")
    void calculate_1() {
        // when
        int result = calculator.calculate("");
        // then
        Assertions.assertEquals(result, 0);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2+3*4/2:10",
            "1+2*3/3:3"},
            delimiter = ':')
    @DisplayName("사칙연산 우선순위가 아닌 입력 순사에 의해서 계산이 된다")
    void calculate_2(String input, Integer expected) {
        // when
        int result = calculator.calculate(input);
        // then
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2 + 3 * 4 / 2:10",
            "1  +  2  *  3  /  3  :3"},
            delimiter = ':')
    @DisplayName("수식에 뛰어쓰기가 포함되어 있는 경우에도 올바르게 계산이 된다")
    void calculate_3(String input, Integer expected) {
        // when
        int result = calculator.calculate(input);
        // then
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2++3:10",
            "1+2*3/",
            "*1+3-1"
    })
    @DisplayName("수식이 올바르지 않은 경우 에러를 반환한다")
    void calculate_4(String input) {
        // when & then
        Assertions.assertThrows(RuntimeException.class, () -> calculator.calculate(input));
    }
}