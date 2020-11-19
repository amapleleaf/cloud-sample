package com.sample.common.util;

import com.sample.common.model.ResponseCodeEnum;

/**
 * 基础异常
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;
	private String errcode;

	public BaseException(String errcode, String message) {
		super(message);
		this.errcode = errcode;
	}

	public BaseException(String errcode, String message, Throwable cause) {
		super(message, cause);
		this.errcode = errcode;
	}

	public String getErrcode() {
		return errcode;
	}

	public String getMessage() {
		StringBuffer sb = new StringBuffer("");
		if (errcode != null) {
			sb.append(errcode + ":");
		}
		sb.append(super.getMessage());
		return sb.toString();
	}

	public String getSuperMessage() {
		return super.getMessage();
	}

}
