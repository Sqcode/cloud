package sqc.goods.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sqc.entity.vo.Result;
import sqc.enums.UserErrorTypeEnum;
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

    /**
     * @Validated 参数验证
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result baseException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.debug("Request URI ：{}, Message：{}", request.getRequestURI(), e.getMessage());
        return Result.fail(UserErrorTypeEnum.A0421, e.getBindingResult().getFieldError().getDefaultMessage());
//        return Result.fail(e.getBindingResult().getFieldError().getDefaultMessage());
    }


    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception e) {
        log.error("Exception:{}", e.getMessage());
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result throwable() {
        return Result.fail();
    }
}
