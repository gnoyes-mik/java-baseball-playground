package game.numberbaseball;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Answer {
    private final int[] answer;

    public Answer(int digitNumber) {
        Random random = new Random();
        this.answer = new int[digitNumber];
        Set<Integer> randomSet = new HashSet<>();

        while (randomSet.size() < digitNumber) {
            randomSet.add(random.nextInt(10));
        }

        int idx = 0;
        for (int number : randomSet) {
            answer[idx++] = number;
        }
    }

    public Answer(int[] answer) {
        this.answer = answer;
    }

    public int checkStrike(int value) {
        int strike = 0;
        char[] chars = String.valueOf(value).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            strike += compareCharWithInt(chars[i], answer[i]);
        }
        return strike;
    }

    private int compareCharWithInt(char ch, int i) {
        if (i == Character.getNumericValue(ch)) {
            return 1;
        }
        return 0;
    }

    public int checkBall(int value) {
        int ball = 0;
        char[] chars = String.valueOf(value).toCharArray();
        for (char aChar : chars) {
            ball += containsCharInIntArray(aChar);
        }
        if (ball == 0) {
            return -1;
        }
        return ball - checkStrike(value);
    }

    private int containsCharInIntArray(char ch) {
        boolean anyMatched = Arrays.stream(answer)
                .anyMatch(number ->
                        number == Character.getNumericValue(ch)
                );
        if (anyMatched) {
            return 1;
        }
        return 0;
    }

    public boolean checkAnswer(int ball, int strike) {
        if (strike == answer.length) {
            ResultView.correctAnswerScript();
            return true;
        }
        ResultView.wrongAnswerScript(ball, strike);
        return false;
    }
}
