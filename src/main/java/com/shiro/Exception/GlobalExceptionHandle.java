package com.shiro.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.shiro.entity.Result;

/**
 * 全局异常捕获
 * @author guigl
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandle {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);
	
	@ExceptionHandler(value = Exception.class)
	public Result<?> handle(Exception e) {
		if (e instanceof GlobalException) {
			GlobalException globalException = (GlobalException)e;
			return Result.error(globalException.getCommonErrorCode());
		} else {
			logger.error("系统异常", e);
			return Result.error(CommonErrorCode.EXCEPTION);
		}
	}
}
