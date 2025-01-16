package main.java.desk.models.appservices;

public class BusinessAppException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String code;

    public BusinessAppException(String message) {
        super(message);
    }

    public BusinessAppException(Throwable cause) {
        super(cause);
    }

    public BusinessAppException(String errorCode, String message) {
        this.code = errorCode;
    }

    public String getErrorCode() {
        return code;
    }

}