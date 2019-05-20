package com.djsenglish.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author shuo
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse <T>{

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess()
    {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static ServerResponse createBySuccess()
    {
        return new ServerResponse(ResponseCode.SUCCESS.getCode());
    }

    public static ServerResponse createBySuccessMsg(String msg)
    {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse createBySuccess(T data)
    {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse createBySuccess(String msg, T data)
    {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static ServerResponse createByError()
    {

        return new ServerResponse(ResponseCode.ERROR.getCode());
    }

    public static ServerResponse createByErrorMsg(String msg)
    {
        return new ServerResponse(ResponseCode.ERROR.getCode(), msg);
    }

    public static <T> ServerResponse createByError(T data)
    {
        return new ServerResponse(ResponseCode.ERROR.getCode(), data);
    }

    public static ServerResponse createByCodeErrorMsg(int status, String msg)
    {
        return new ServerResponse(status, msg);
    }

}
