package game.numberbaseball;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AnswerTest {

    @ParameterizedTest
    @CsvSource(value = {"248:0", "123:1", "139:2", "159:3"}
            , delimiter = ':')
    @DisplayName("같은 숫자가 같은 자리에 있으면 그 개수 만큼 스트라이크를 반환한다")
    void checkStrike(int input, int expectedStrike) {
        // given
        Answer answer = new Answer(new int[]{1, 5, 9});

        // when & then
        Assertions.assertEquals(answer.checkStrike(input), expectedStrike);
    }

    @ParameterizedTest
    @CsvSource(value = {"213:1", "931:2", "519:2", "159:0"}
            , delimiter = ':')
    @DisplayName("다른 자리에 숫자가 있으면 그 개수 만큼 볼을 반환한다")
    void checkBall_1(int input, int expectedBall) {
        // given
        Answer answer = new Answer(new int[]{1, 5, 9});

        // when & then
        Assertions.assertEquals(answer.checkBall(input), expectedBall);
    }

    @ParameterizedTest
    @CsvSource(value = {"248:-1"}, delimiter = ':')
    @DisplayName("같은 수가 전혀 없으면 -1을 반환한다")
    void checkBall_2(int input, int expectedBall) {
        // given
        Answer answer = new Answer(new int[]{1, 5, 9});

        // when & then
        Assertions.assertEquals(answer.checkBall(input), expectedBall);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:3:true", "3:0:false", "1:2:false"}, delimiter = ':')
    @DisplayName("정답이면 true 오답이면 false를 반환한다")
    void checkAnswer(int ball, int strike, boolean expected) {
        // given
        Answer answer = new Answer(new int[]{1, 5, 9});

        // when & then
        Assertions.assertEquals(answer.checkAnswer(ball, strike), expected);
    }

}