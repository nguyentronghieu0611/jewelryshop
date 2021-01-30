package vn.jewel.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException() {
        super("ResourceNotFoundException");
    }

}
