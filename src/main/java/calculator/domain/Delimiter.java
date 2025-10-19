package calculator.domain;


import calculator.exception.InputException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Delimiter {

    //개행과 리터럴 백슬래쉬(\)와 문자(n)의 조합에 대한 오류가 발생
    //두 경우에 대한 처리가능하게 정리
    private static final Pattern CUSTOM = Pattern.compile("//(.)\\s*(?:\n|\\\\n)(.*)");
    private static final String DEFAULT_DELIMITER = ",|:";

    private final String splitRegex; //구분자 정규식
    private final String numbers; //숫자부분


    private Delimiter(String splitRegex, String numbers){
        this.splitRegex = splitRegex;
        this.numbers = numbers;
    }

    public static Delimiter fromText(String text){
        //빈 문자열일 경 0 반환
        if(text == null || text.isBlank()){
            return new Delimiter(DEFAULT_DELIMITER , "0");
        }
        //구분자가 하나가 아닐 경우 대한 예외처리
        Matcher m  = CUSTOM.matcher(text);
        if (m.matches()){
            String custom = m.group(1);
            if (custom.length() != 1){
                throw new IllegalArgumentException(InputException.CUSTOM_DELIMITER_SINGLE_PRINCIPLE.getMessage());
            }
            String quoted = Pattern.quote(custom);
            //커스텀 구분자와 기본 구분자를 중복으로 연산 가능하게 처리
            return new Delimiter(DEFAULT_DELIMITER + "|" +  quoted, m.group(2));
        }
        return new Delimiter(DEFAULT_DELIMITER, text);
    }
    public String[] split(){ return numbers.split(splitRegex);}
}
