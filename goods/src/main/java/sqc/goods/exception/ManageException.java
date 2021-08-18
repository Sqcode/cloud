package sqc.goods.exception;


import sqc.exception.BaseException;
import sqc.goods.enums.ManageExceptionEnum;

/**
 * @Description: 后台接口异常
 * @author: Sqcode
 * @since: 2021/5/25 10:00
 */
public class ManageException extends BaseException {

    public ManageException(ManageExceptionEnum manageExceptionEnum) {
        super(manageExceptionEnum);
    }

    public ManageException(ManageExceptionEnum manageExceptionEnum, String msg) {
        super(manageExceptionEnum, msg);
    }
}
