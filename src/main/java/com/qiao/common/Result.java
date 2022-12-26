package com.qiao.common;

import com.qiao.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Mr.Qiao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 返回响应数据
     */
    private T data;
    /**
     * 返回响应码
     */
    private String respCode;
    /**
     * 返回响描述
     */
    private String message;

    public static <T> Result<T> succeed(String msg) {
        return succeedWith(null, CodeEnum.SUCCESS.getCode(), msg);
    }
    public static <T> Result<T> succeed() {
        return succeedWith(null, CodeEnum.SUCCESS.getCode(), "操作成功");
    }

    public static <T> Result<T> succeed(T model,String msg) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(), "成功");
    }

    public static <T> Result<T> succeedWith(T datas, String code, String msg) {
        return new Result<>(datas, code, msg);
    }

    public static <T> Result<T> failed(String msg) {
        return failedWith(null, CodeEnum.FAIL.getCode(), msg);
    }
    public static <T> Result<T> failed() {
        return failedWith(null, CodeEnum.FAIL.getCode(), "操作失败");
    }
    public static <T> Result<T> paramFailed(String msg) {
        return failedWith(null, CodeEnum.FAIL.getCode(), msg);
    }
    public static <T> Result<T> failed(T model, String msg) {
        return failedWith(model, CodeEnum.FAIL.getCode(), msg);
    }

    public static <T> Result<T> failedWith(T datas, String code, String msg) {
        return new Result<>(datas, code, msg);
    }

}
