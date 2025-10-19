package calculator.exception;

public enum InputException {

    OPERAND_NUMBER_PRICIPLE("피연사자는 오직 숫자"),
    OPERAND_POSITIVE_PRICIPLE("피연산자는 오직 양수"),
    CUSTOM_DELIMITER_SINGLE_PRINCIPLE("커스텀 구분자는 오직 하나");

    private String message;
    InputException(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
