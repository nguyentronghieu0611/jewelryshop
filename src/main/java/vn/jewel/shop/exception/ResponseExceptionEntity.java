package vn.jewel.shop.exception;

import java.io.Serializable;

public class ResponseExceptionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int status;
    private String message;

    private Object error;

    public ResponseExceptionEntity(int status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public ResponseExceptionEntity(int status, String message, Object error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

}
