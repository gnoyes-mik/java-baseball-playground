import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;

/**
 * 문자열 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다. <br>
 * 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다. <br>
 * 예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.
 */
public class StringCalculator {
    private final Queue<Integer> numbers = new LinkedList<>();
    private final Queue<String> operations = new LinkedList<>();
    private int result = 0;

    public int calculate(String input) {
        if (Objects.isNull(input) || input.isEmpty()) {
            return 0;
        }
        setUp(input);
        while (isRemainedOperation()) {
            String operation = getOperation();
            int n = getNumber();

            switch (operation) {
                case "+":
                    result = plus(result, n);
                    break;
                case "-":
                    result = minus(result, n);
                    break;
                case "*":
                    result = multiply(result, n);
                    break;
                case "/":
                    result = divide(result, n);
                    break;
            }
        }
        return result;
    }

    private int plus(int a, int b) {
        return a + b;
    }

    private int minus(int a, int b) {
        return a - b;
    }

    private int multiply(int a, int b) {
        return a * b;
    }

    private int divide(int a, int b) {
        return a / b;
    }

    private void setUp(String input) {
        input = input.replaceAll(" ", "");
        for (int i = 0; i < input.length(); i++) {
            // number case
            if (i % 2 == 0) {
                int number = Integer.parseInt(String.valueOf(input.charAt(i)));
                offerNumber(number);
            }

            // operation case
            if (i % 2 == 1) {
                String operation = String.valueOf(input.charAt(i));
                offerOperation(operation);
            }
        }
        result = getNumber();
    }

    private boolean offerNumber(int number) {
        return this.numbers.offer(number);
    }

    private int getNumber() {
        try {
            return this.numbers.remove();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("숫자 입력이 올바르지 않습니다.");
        }
    }

    private boolean offerOperation(String operation) {
        if (Objects.isNull(operation)) {
            throw new RuntimeException("사칙연산 입력이 올바르지 않습니다.");
        }
        if ("+".equals(operation) || "-".equals(operation) || "*".equals(operation) || "/".equals(operation)) {
            return this.operations.offer(operation);
        }
        throw new RuntimeException("사칙연산 입력이 올바르지 않습니다.");
    }

    private String getOperation() {
        try {
            return this.operations.remove();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("사칙연산 입력이 올바르지 않습니다.");
        }
    }

    private boolean isRemainedNumber() {
        return !this.numbers.isEmpty();
    }

    private boolean isRemainedOperation() {
        return !this.operations.isEmpty();
    }
}
