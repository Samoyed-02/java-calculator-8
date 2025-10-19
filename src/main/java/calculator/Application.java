

package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.*;
import java.util.regex.*;

public class Application {

    // 문자열 계산
    public int Calculater(String text) {
        int result = 0;
        for (String txt : splitText(text)) {
            result += parseNum(txt.trim());
        }
        return result;
    }

    // 문자열 분리
    private String[] splitText(String text) {
        if (text.isEmpty()) return new String[]{"0"};

        String delimiter = ",|:";  // 기본 구분자
        String numbers = text;

        Matcher m = Pattern.compile("//(.)\\s*(?:\n|\\\\n)(.*)").matcher(text); // \n(개힝)과 \\\\n(리터럴\ + n) 을 모두 인식

        if (m.find()) {
            String custom = Pattern.quote(m.group(1)); // 구분자 특수문자 안전화
            delimiter = delimiter +  "|" + custom; //기본 구문자 + 커스텀 구문자
            numbers = m.group(2);                  // 숫자 부분
        }

        return Arrays.stream(numbers.split(delimiter))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    // 숫자 검증
    private int parseNum(String text) {
        try {
            int num = Integer.parseInt(text);
            if (num < 0) throw new IllegalArgumentException("음수 허용 x");
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 변환 x: '" + text + "'");
        }
    }

    // 메인
    public static void main(String[] args) {
        Application app = new Application(); //메서드 호출을 위한 객체 설정

        System.out.println("덧셈할 문자열을 입력해주세요.");
        String inputMessage = Console.readLine();

        int sum = app.Calculater(inputMessage);
        System.out.println("결과 : " + sum);
    }
}