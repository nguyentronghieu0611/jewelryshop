package vn.jewel.shop.util;

import org.apache.commons.validator.GenericValidator;
import vn.jewel.shop.dto.DataFieldErrorDto;
import vn.jewel.shop.dto.DataPostDto;

import java.util.List;

public class Utils {
    public static boolean isEmptyText(String txt) {
        return txt == null || txt.trim().isEmpty();
    }

    public static boolean isEmptyId(Long value) {
        return value == null || value.longValue() == 0;
    }

    public static boolean isEmptyList(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean equal(Long v1, Long v2) {
        return v1 != null && v1.equals(v2);
    }

    public static boolean equal(String v1, String v2) {
        return v1 != null && v1.equals(v2);
    }

    public static boolean validateDataField(DataPostDto.MetaDto meta) {
        if(meta == null) {
            return false;
        }
        String value = meta.getFieldValue();
        if(meta.getIsNull() == 0 && isEmptyText(value)) {
            return false;
        }
        if("Number".equalsIgnoreCase(meta.getType()) && !isNumeric(value)) {
            return false;
        }
        if("Datetime".equalsIgnoreCase(meta.getType())
                && !GenericValidator.isDate(value, "dd/MM/yyyy", true) 
                && !GenericValidator.isDate(value, "dd-MM-yyyy", true)) {
            return false;
        }
        return true;
    }

    public static DataFieldErrorDto checkDataField(DataPostDto.MetaDto meta) {
        if(meta == null) {
            return new DataFieldErrorDto("empty", "Không có dữ liệu");
        }
        String value = meta.getFieldValue();
        if(meta.getIsNull() == 0 && isEmptyText(value)) {
            return new DataFieldErrorDto(meta.getFieldCode(), meta.getFieldName()+" ("+meta.getFieldCode()+") Không được để trống");
        }
        if("Number".equalsIgnoreCase(meta.getType()) && !isNumeric(value)) {
            return new DataFieldErrorDto(meta.getFieldCode(), meta.getFieldName()+" ("+meta.getFieldCode()+") Phải là kiểu số");
        }
        if("Datetime".equalsIgnoreCase(meta.getType())
                    && !isEmptyText(value)
                    && !GenericValidator.isDate(value, "dd/MM/yyyy", true) 
                    && !GenericValidator.isDate(value, "dd-MM-yyyy", true)) {
                return new DataFieldErrorDto(meta.getFieldCode(), meta.getFieldName()+" ("+meta.getFieldCode()+") Phải là kiểu thời gian dd/MM/yyyy");
        }
        return null;
    }

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }


}
