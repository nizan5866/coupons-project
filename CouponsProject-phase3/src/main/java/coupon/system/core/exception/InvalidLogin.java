package coupon.system.core.exception;

public class InvalidLogin extends Exception{

    public InvalidLogin() {
    }

    public InvalidLogin(String message) {
        super(message);
    }

    public InvalidLogin(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLogin(Throwable cause) {
        super(cause);
    }

    public InvalidLogin(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
