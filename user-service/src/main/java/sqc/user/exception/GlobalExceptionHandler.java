package sqc.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sqc.entity.vo.Result;
import sqc.exception.BaseException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 全局异常拦截
 * @Author: Sqcode
 * @Date: 2021/5/21 8:47
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Result baseException(BaseException e, HttpServletRequest request) {
        log.debug("Request URI ：{}, Message：{}", request.getRequestURI(), e.getMessage());
        return Result.fail(e, e.getMessage());
    }
}
