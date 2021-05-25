package sqc.user.exception;

import lombok.Data;
import sqc.exception.BaseException;
import sqc.exception.ErrorType;
import sqc.user.enums.BusinessErrorTypeEnum;

/**
 * @Description:
 * @author: Sqcode
 * @since: 2021/4/29 8:28
 */
@Data
public class BusinessException extends BaseException {

    public BusinessException(BusinessErrorTypeEnum businessErrorTypeEnum) {
        super(businessErrorTypeEnum);
    }

    public BusinessException(ErrorType errorType, String message) {
        super(errorType, message);
    }

    public BusinessException(ErrorType errorType, String message, Throwable cause) {
        super(errorType, message, cause);
    }

}
