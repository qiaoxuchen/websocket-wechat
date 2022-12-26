package com.qiao.enums;

/**
 * @Author: Mr.Qiao
 */
public enum CodeEnum {
    SUCCESS("0000","操作成功"),
    FAIL("0001","操作失败"),


    UNPROCESABLE_ENTITY("4100","参数验证错误"),


    INTERNAL_SERVER_ERROR("5000","服务器发生错误"),

    UN_KNOW_ERROR("9999","未知错误"),
    DB_ERROR("1111","数据库异常");


    private final String code;
    private final String message;

    CodeEnum(final String code, final String message){
        this.code=code;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
