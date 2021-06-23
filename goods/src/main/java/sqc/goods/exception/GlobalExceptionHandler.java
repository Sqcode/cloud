package sqc.goods.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sqc.entity.vo.Result;
import sqc.exception.BaseException;
import sqc.exception.SystemErrorType;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 全局异常拦截
 * @author: Sqcode
 * @since: 2021/5/21 8:47
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Result baseException(BaseException e, HttpServletRequest request) {
        log.error("Request URI ：{}, Message：{}", request.getRequestURI(), e);
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
        log.error("Request URI ：{}, Message：{}", request.getRequestURI(), e);
        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception e, HttpServletRequest request) {
        log.error("Request URI ：{}, Exception: {}", request.getRequestURI(), e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result throwable(Throwable e, HttpServletRequest request) {
        log.error("Request URI ：{}, Throwable: {}", request.getRequestURI(), e);
        return Result.fail(e.getMessage());
    }
}