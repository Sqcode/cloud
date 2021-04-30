package sqc.user.exception;

import lombok.Data;
import sqc.exception.BaseException;
import sqc.user.enums.BusinessErrorTypeEnum;

/**
 * @Description:
 * @Author: Sqcode
 * @Date: 2021/4/29 8:28
 */
@Data
public class BusinessException extends BaseException {

    public BusinessException(BusinessErrorTypeEnum businessErrorTypeEnum) {
        super(businessErrorTypeEnum, businessErrorTypeEnum.getMsg());
    }

//    public BusinessException(BusinessErrorTypeEnum businessErrorTypeEnum) {
//        super(businessErrorTypeEnum);
//    }

//    public BusinessException(ErrorType errorType, String message) {
//        super(errorType, message);
//    }
//
//    public BusinessException(ErrorType errorType, String message, Throwable cause) {
//        super(errorType, message, cause);
//    }

}
