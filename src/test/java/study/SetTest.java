package study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    @DisplayName("size()를 활용해 Set의 사이즈를 확인한다")
    void size() {
        // when
        int result = numbers.size();

        // then
        Assertions.assertEquals(result, 3);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:True", "2:True", "5:False", "10:False"}, delimiter = ':')
    @DisplayName("contains()를 활용해 Set에 해당 원소가 포함되어 있는지 확인한다")
    void contains(Integer number, Boolean expected) {
        // when & then
        Assertions.assertEquals(numbers.contains(number), expected);
    }

}
