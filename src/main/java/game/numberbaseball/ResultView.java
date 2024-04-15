package game.numberbaseball;

class ResultView {
    static void wrongAnswerScript(int ball, int strike) {
        StringBuilder sb = new StringBuilder();
        if (ball > 0) {
            sb.append(ball).append("볼 ");
        }
        if (strike > 0) {
            sb.append(strike).append("스트라이크 ");
        }
        if (ball == 0 && strike == 0) {
            sb.append("NOTHING!");
        }
        System.out.println(sb);
    }

    static void correctAnswerScript() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료");
    }
}