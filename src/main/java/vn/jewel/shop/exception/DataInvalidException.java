package vn.jewel.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import vn.jewel.shop.dto.DataFieldErrorDto;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataInvalidException extends RuntimeException {
    private static final long serialVersionUID = 1;

    private DataFieldErrorDto error;

    public DataInvalidException(DataFieldErrorDto error) {
        super(error.getMessage());
    }

    public DataFieldErrorDto getError() {
        return error;
    }

    public void setError(DataFieldErrorDto error) {
        this.error = error;
    }

}
