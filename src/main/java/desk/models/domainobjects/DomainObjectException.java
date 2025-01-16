package main.java.desk.models.domainobjects;

public class DomainObjectException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String code;

    public DomainObjectException(String message) {
        super(message);
    }

    public DomainObjectException(String errorCode, String message) {
        this.code = errorCode;
    }

    public String getErrorCode() {
        return code;
    }
}