package game.numberbaseball;

import java.util.*;

import static game.numberbaseball.NumberBaseBallGameSimulator.Command.EXIT;
import static game.numberbaseball.NumberBaseBallGameSimulator.Command.RESTART;

public class NumberBaseBallGameSimulator {
    private final RuleManager ruleManager;
    private final Scanner scanner = new Scanner(System.in);

    public NumberBaseBallGameSimulator() {
        this.ruleManager = new RuleManager(3);
    }

    public NumberBaseBallGameSimulator(int digitNumber) {
        this.ruleManager = new RuleManager(digitNumber);
    }

    public NumberBaseBallGameSimulator(int[] answer) {
        this.ruleManager = new RuleManager(answer.length, answer);
    }

    public void start() {
        int ball;
        int strike;
        do {
            InputView.inputScript();
            int value = waitIntegerInput();
            strike = checkStrike(value);
            ball = checkBall(value);
        } while (!checkAnswer(ball, strike));

        InputView.restartScript();
        int value = waitIntegerInput();
        restart(value);
    }

    private int checkStrike(int value) {
        return ruleManager.checkStrike(value);
    }

    private int checkBall(int value) {
        return ruleManager.checkBall(value);
    }

    private boolean checkAnswer(int ball, int strike) {
        return ruleManager.checkAnswer(ball, strike);
    }

    private int waitIntegerInput() {
        try {
            int input = scanner.nextInt();
            ruleManager.validateInput(input);
            return input;
        } catch (NumberFormatException e) {
            throw new RuntimeException("숫자만 입력 가능합니다.");
        }
    }

    private void restart(int value) {
        if (EXIT == Command.of(value)) {
            return;
        }
        if (RESTART == Command.of(value)) {
            ruleManager.reset();
            start();
        }
    }

    enum Command {
        RESTART(1),
        EXIT(2);

        private final int code;

        Command(int code) {
            this.code = code;
        }

        public static Command of(int code) {
            return Arrays.stream(Command.values())
                    .filter(command -> command.code == code)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 명령어 입니다."));
        }
    }
}
