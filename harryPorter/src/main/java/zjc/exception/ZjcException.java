package zjc.exception;

public class ZjcException extends RuntimeException {
    private final String businessType;

    public ZjcException(String msg) {
        super(msg);
        this.businessType = "";
    }

    public ZjcException(String msg, Throwable cause) {
        super(msg, cause);
        this.businessType = "";
    }

    public ZjcException(String msg, String businessType) {
        super(msg);
        this.businessType = businessType;
    }

    public ZjcException(String msg, Throwable cause, String businessType) {
        super(msg, cause);
        this.businessType = businessType;
    }
}
