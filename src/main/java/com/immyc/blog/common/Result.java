package com.immyc.blog.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

public class Result implements Serializable {

    private Integer code;
    private String msg;
    private Object data;

    public Result() {
        this.code = ResultCode.SUCCESS;
        this.msg = ResultMsg.SUCCESS;
        this.data = "";
    }

    public Result(Object data) {
        this.code = ResultCode.SUCCESS;
        this.msg = ResultMsg.SUCCESS;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result(Integer code, Object data) {
        this.code = code;
        this.msg = ResultMsg.SUCCESS;
        this.data = data;
    }

    public Result(String msg, Object data) {
        this.code = ResultCode.SUCCESS;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result ok(String msg, Object data) {
        return new Result(msg, data);
    }

    public static Result error() {
        return new Result(ResultCode.SYSTEM_ERROR);
    }

    public static Result error(Integer code) {
        return new Result(code);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static class ResultCode {
        public static final Integer SUCCESS = 200;
        public static final Integer SYSTEM_ERROR = 500;
        public static final Integer DATA_LOSS = 600;
        public static final Integer INVALID_PARAMS = 601;
    }

    public static class ResultMsg {
        public static final String SUCCESS = "SUCCESS";
        public static final String SYSTEM_ERROR = "SYSTEM_ERROR";
    }
}
