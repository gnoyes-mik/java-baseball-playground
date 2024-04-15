package game.numberbaseball;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RuleManagerTest {

    @Test
    @DisplayName("입력 값이 음수인 경우에 에러가 반환된다")
    void validateInput_1() {
        // given
        RuleManager ruleManager = new RuleManager();

        // when & then
        Assertions.assertThrows(RuntimeException.class, () -> ruleManager.validateInput(-1));
    }

    @Test
    @DisplayName("입력 값이 최대 자리수보다 큰 경우에 에러가 반환된다")
    void validateInput_2() {
        // given
        RuleManager ruleManager1 = new RuleManager();  // 1~999
        RuleManager ruleManager2 = new RuleManager(4); // 1~9999

        // when & then
        Assertions.assertThrows(RuntimeException.class, () -> ruleManager1.validateInput(1_000));
        Assertions.assertDoesNotThrow(() -> ruleManager2.validateInput(1_000));
        Assertions.assertThrows(RuntimeException.class, () -> ruleManager2.validateInput(10_000));
    }

    @ParameterizedTest
    @CsvSource(value = {"248:0", "123:1", "139:2", "159:3"}
            , delimiter = ':')
    @DisplayName("같은 숫자가 같은 자리에 있으면 그 개수 만큼 스트라이크를 반환한다")
    void checkStrike(int input, int expectedStrike) {
        // given
        RuleManager ruleManager = new RuleManager(3, new int[]{1, 5, 9});

        // when & then
        Assertions.assertEquals(ruleManager.checkStrike(input), expectedStrike);
    }

    @ParameterizedTest
    @CsvSource(value = {"213:1", "931:2", "519:2", "159:0"}
            , delimiter = ':')
    @DisplayName("다른 자리에 숫자가 있으면 그 개수 만큼 볼을 반환한다")
    void checkBall_1(int input, int expectedBall) {
        // given
        RuleManager ruleManager = new RuleManager(3, new int[]{1, 5, 9});

        // when & then
        Assertions.assertEquals(ruleManager.checkBall(input), expectedBall);
    }

    @ParameterizedTest
    @CsvSource(value = {"248:-1"}, delimiter = ':')
    @DisplayName("같은 수가 전혀 없으면 -1을 반환한다")
    void checkBall_2(int input, int expectedBall) {
        // given
        RuleManager ruleManager = new RuleManager(3, new int[]{1, 5, 9});

        // when & then
        Assertions.assertEquals(ruleManager.checkBall(input), expectedBall);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:3:true", "3:0:false", "1:2:false"}, delimiter = ':')
    @DisplayName("정답이면 true 오답이면 false를 반환한다")
    void checkAnswer(int ball, int strike, boolean expected) {
        // given
        RuleManager ruleManager = new RuleManager(3, new int[]{1, 5, 9});

        // when & then
        Assertions.assertEquals(ruleManager.checkAnswer(ball, strike), expected);
    }
}