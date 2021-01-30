package vn.jewel.shop.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {
    private int err_code;
    private String err_msg;


    public ResponseModel(int err_code, String err_msg) {
        this.err_code = err_code;
        this.err_msg = err_msg;
    }
}
