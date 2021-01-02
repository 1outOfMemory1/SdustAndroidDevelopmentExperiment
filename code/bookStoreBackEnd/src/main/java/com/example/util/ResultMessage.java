package com.example.util;


import java.io.Serializable;

public class ResultMessage implements Serializable {
    public static class code{
        static public Integer SUCCESS = 200;
        static public Integer FAILURE = 300;
    }

    public static class message{
        static public String SUCCESS = "SUCCESS";
        static public String FAILURE = "FAILURE";
    }




    private Integer code;
    private String message;
    private Object data;

    public ResultMessage(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
