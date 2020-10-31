package com.simple4code.simple4j.core.handler;


import com.simple4code.simple4j.core.entity.Result;
import com.simple4code.simple4j.core.entity.ResultCode;
import com.simple4code.simple4j.core.exception.CommonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的公共异常处理器
 * 1.声明异常处理器
 * 2.对异常统一处理
 */
@ControllerAdvice
public class BaseExceptionHandler {

    /**
     * 未授权
     */

  /*  @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response, AuthorizationException e) {
        return new Result(ResultCode.UNAUTHORISE);
    }
    *//*   *//*
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        if (e.getClass() == CommonException.class) {
            //类型转型
            CommonException ce = (CommonException) e;
            Result result = new Result(ce.getResultCode());
            return result;
        } else {
            Result result = new Result(ResultCode.SERVER_ERROR);
            return result;
        }
    }

  /*  @Bean
    public ErrorAttributes errorAttributes() {
        // Hide exception field in the return object
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
                errorAttributes.remove("exception");
                return errorAttributes;
            }
        };
    }*/

    //@ExceptionHandler(CustomException.class)
    //public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
    //    res.sendError(ex.getHttpStatus().value(), ex.getMessage());
    //}
    //
    //@ExceptionHandler(AccessDeniedException.class)
    //public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
    //    res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
    //}
    //
    //@ExceptionHandler(Exception.class)
    //public void handleException(HttpServletResponse res) throws IOException {
    //    res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
    //}

}
