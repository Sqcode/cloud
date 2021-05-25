package sqc.user.exception;

import sqc.enums.UserErrorTypeEnum;
import sqc.exception.BaseException;
import sqc.exception.ErrorType;

/**
 * @Description: 用户类错误
 * @author: Sqcode
 * @since: 2021/5/21 10:35
 */
public class UserException extends BaseException {

    public UserException(UserErrorTypeEnum userErrorTypeEnum) {
        super(userErrorTypeEnum);
    }

    public UserException(ErrorType errorType, String message) {
        super(errorType, message);
    }

    public UserException(ErrorType errorType, String message, Throwable cause) {
        super(errorType, message, cause);
    }

}
