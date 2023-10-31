package com.example.validated.model.enumCls;


import org.springframework.util.StringUtils;

public enum  SexTypeEnum {

    MAN(1,"男"),
    WOMAN(2, "女");

    private int code;
    private String desc;

    SexTypeEnum(int code , String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static boolean isValueValid(Integer value) {
        if(!StringUtils.isEmpty(value)){
            for (SexTypeEnum enumObj : SexTypeEnum.values()) {
                if (enumObj.getCode().equals(value)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

}
