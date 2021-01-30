package vn.jewel.shop.dto;

import java.io.Serializable;
import java.util.List;

public class DataPostDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long cateId;
    private String cateName;
    private List<MetaDto> meta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public List<MetaDto> getMeta() {
        return meta;
    }

    public void setMeta(List<MetaDto> meta) {
        this.meta = meta;
    }

    public static class MetaDto implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long id;
        private String fieldValue;
        private String type;
        private int isNull;
        private Long fieldId;
        private String fieldName;
        private String fieldCode;
        private Integer displayOrder = 0;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getIsNull() {
            return isNull;
        }

        public void setIsNull(int isNull) {
            this.isNull = isNull;
        }

        public Long getFieldId() {
            return fieldId;
        }

        public void setFieldId(Long fieldId) {
            this.fieldId = fieldId;
        }

        public Integer getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(Integer displayOrder) {
            this.displayOrder = displayOrder;
        }

        public String getFieldCode() {
            return fieldCode;
        }

        public void setFieldCode(String fieldCode) {
            this.fieldCode = fieldCode;
        }
    }
}
