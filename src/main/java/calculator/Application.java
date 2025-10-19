package calculator;

import calculator.domain.TextCalculater;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        TextCalculater calculator = new TextCalculater();

        System.out.println("덧셈할 문자열을 입력해주세요.");
        String input = Console.readLine();

        int result = calculator.calculate(input);
        System.out.println("결과 : " + result);

    }
}
