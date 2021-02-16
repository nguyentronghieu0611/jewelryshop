package vn.jewel.shop.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotEnableException extends AuthenticationException {

    public UserNotEnableException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotEnableException(String msg) {
        super(msg);
    }
}
