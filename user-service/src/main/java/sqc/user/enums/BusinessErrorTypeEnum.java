package sqc.user.enums;

import sqc.exception.ErrorType;

/**
 * @Description:
 * @Author: Sqcode
 * @Date: 2021/4/30 14:48
 */
public enum BusinessErrorTypeEnum implements ErrorType {

    ACCOUNT_OR_PASSWORD_ERROE("10000", "账号或密码错误！");


    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    BusinessErrorTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
