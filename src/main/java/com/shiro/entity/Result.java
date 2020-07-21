package com.shiro.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.shiro.Exception.CommonErrorCode;

import java.io.Serializable;

/**
 *  response返回封装
 */
public class Result<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 是否成功
     */
    private boolean success;

    /**
     * 服务器当前时间戳
     */
    private Long systemTime = System.currentTimeMillis();

    /**
     * 成功数据
     */
    private T data;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误描述
     */
    private String msg;

    private int count;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Long systemTime) {
        this.systemTime = systemTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Result(int code, String msg,T data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
    
    public static Result<?> error(int code, String msg) {
        Result<?> result = new Result<>();
        result.success = false;
        result.code = code;
        result.msg = msg;
        return result;
    }

    public Result<Object> result(boolean success,int code, String msg, Object data) {
        Result<Object> result = new Result<>();
        result.success = success;
        result.code = code;
        result.msg = msg;
        result.setData(data);
        return result;
    }

    public static Result<?> error(CommonErrorCode resultEnum) {
        Result<?> result = new Result<>();
        result.success = false;
        result.code = resultEnum.getCode();
        result.msg = resultEnum.getMessage();
        return result;
    }


    /**
     * 获取 json
     */
    public String buildResultJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", this.success);
        jsonObject.put("code", this.code);
        jsonObject.put("systemTime", this.systemTime);
        jsonObject.put("msg", this.msg);
        jsonObject.put("data", this.data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", systemTime=" + systemTime +
                ", data=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
