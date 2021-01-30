package vn.jewel.shop.dto;

import java.io.Serializable;

public class DataFieldErrorDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;

    public DataFieldErrorDto() {

    }

    public DataFieldErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
