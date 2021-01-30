package vn.jewel.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDataModel {
    private int err_code;
    private String err_msg;
    private List<Object> data;

    public ResponseDataModel(int err_code, String err_msg, List<Object> data) {
        this.err_code = err_code;
        this.err_msg = err_msg;
        this.data = data;
    }
}
