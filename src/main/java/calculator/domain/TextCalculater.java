package calculator.domain;


import calculator.exception.InputException;

public class TextCalculater {

    // 구분자로 인해 구분된 문자열을 순회하며  빈 문자열 필터 및 숫자들은 합산
    public int calculate(String text){
        Delimiter Del = Delimiter.fromText(text);
        int sumResult = 0;
        for (String token : Del.split()) {
            if(token.isBlank()) continue;
            int number = parseNumber(token.trim());
            sumResult += number;
        }
        return sumResult;
    }

    //입력받은 문자열에 대한 예외처리 (양수 , 숫자 판단 )
    private int parseNumber(String text){
        try{
            int num = Integer.parseInt(text);
            if (num < 0 ) throw new IllegalArgumentException(InputException.OPERAND_POSITIVE_PRICIPLE.getMessage());
            return num;
        } catch (NumberFormatException e){
            throw new IllegalArgumentException(InputException.OPERAND_NUMBER_PRICIPLE.getMessage() + "(" + text + ")");
        }
    }

}
