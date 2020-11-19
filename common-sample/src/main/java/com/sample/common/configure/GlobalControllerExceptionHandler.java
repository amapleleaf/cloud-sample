package com.sample.common.configure;

import javax.servlet.http.HttpServletRequest;

import com.sample.common.model.ResponseCodeEnum;
import com.sample.common.model.ResponseResult;
import com.sample.common.util.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(HttpServletRequest request, Exception e) {
    	 logger.error("系统异常："+e.getMessage(),e);
         return ResponseResult.error(ResponseCodeEnum.FAIL);
    }

    
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseResult  serviceException(HttpServletRequest request, BaseException e) {
    	 logger.error("业务异常："+e.getMessage(),e);
         return ResponseResult.error(e.getErrcode(), e.getMessage());
    }
    
    
    @ExceptionHandler({BindException.class})
    @ResponseBody
    public ResponseResult  serviceValidException(HttpServletRequest request, Exception ex) {
    	logger.error("验证异常："+ex.getMessage(),ex);
    	String errorMessage = null;
        String errorField = null;
        Object errorValue = null;
         if (ex instanceof BindException) {
             BindException bex = (BindException) ex;
             ObjectError error = bex.getGlobalError();
             if (error != null) {
                 errorMessage = error.getDefaultMessage();
             }
             FieldError error2 = bex.getFieldError();
             if (error2 != null) {
                 errorField = error2.getField();
                 errorMessage = error2.getDefaultMessage();
                 errorValue = error2.getRejectedValue();
             }
         }
         
         ValidationErrorResponse response = new ValidationErrorResponse();
         response.setErrorField(errorField);
         response.setErrorValue(errorValue);
         return ResponseResult.error(ResponseCodeEnum.PARAM_ERROR,response);
    }

    public static class ValidationErrorResponse {
        private static final long serialVersionUID = 1L;

        private String errorField;

        private Object errorValue;

        public String getErrorField() {
            return errorField;
        }

        public void setErrorField(String errorField) {
            this.errorField = errorField;
        }

        public Object getErrorValue() {
            return errorValue;
        }

        public void setErrorValue(Object errorValue) {
            this.errorValue = errorValue;
        }
    }

}