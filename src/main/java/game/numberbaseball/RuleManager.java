package game.numberbaseball;

public class RuleManager {

    private Answer answer;
    private int[] answerForTest;
    private final int DIGIT_NUMBER;

    public RuleManager() {
        this.DIGIT_NUMBER = 3;
        this.answer = new Answer(DIGIT_NUMBER);
    }

    public RuleManager(int digitNumber) {
        this.DIGIT_NUMBER = digitNumber;
        this.answer = new Answer(DIGIT_NUMBER);
    }

    public RuleManager(int digitNumber, int[] answer) {
        this.DIGIT_NUMBER = digitNumber;
        this.answer = new Answer(answer);
        this.answerForTest = answer;
    }

    public void reset() {
        if (answerForTest != null) {
            this.answer = new Answer(answerForTest);
            return;
        }
        this.answer = new Answer(DIGIT_NUMBER);
    }

    public int checkStrike(int value) {
        return answer.checkStrike(value);
    }

    public int checkBall(int value) {
        return answer.checkBall(value);
    }

    public boolean checkAnswer(int ball, int strike) {
        return answer.checkAnswer(ball, strike);
    }

    public void validateInput(int input) {
        if (input < 0) {
            throw new RuntimeException("입력 값(" + input + ") 은 음수일 수 없습니다.");
        }
        double limit = Math.pow(10, DIGIT_NUMBER) - 1;
        if (input > limit) {
            throw new RuntimeException("입력 값(" + input + ") " + limit + "보다 클 수 없습니다.");
        }
    }
}
