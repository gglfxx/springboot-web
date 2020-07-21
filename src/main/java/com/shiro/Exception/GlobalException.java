package com.shiro.Exception;

/**
 * 自定义异常
 * @author guigl
 *
 */
public class GlobalException extends RuntimeException {
	
	private static final long serialVersionUID = -7034897190745766939L;
	
	private int code;
	
	private CommonErrorCode commonErrorCode;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public GlobalException(int code,String message) {
		super(message);
		this.code = code;
	}
	
	public CommonErrorCode getCommonErrorCode() {
		return commonErrorCode;
	}

	public void setCommonErrorCode(CommonErrorCode commonErrorCode) {
		this.commonErrorCode = commonErrorCode;
	}

	/**
	 * 通过枚举的方式赋值异常
	 * @param commonErrorCode
	 */
	public GlobalException(CommonErrorCode commonErrorCode){
		this.commonErrorCode = commonErrorCode;
	}
	
}
