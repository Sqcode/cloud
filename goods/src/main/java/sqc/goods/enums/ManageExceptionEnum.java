package sqc.goods.enums;


import sqc.exception.ErrorType;

/**
 * @Description: 后台错误 类型
 * @author: Sqcode
 * @since: 2021/5/25 10:03
 */
public enum ManageExceptionEnum implements ErrorType {

    EXPORT_ERROR("10001", "导出失败"),
    FILE_PARSING_ERROR("10002", "文件解析错误");

    private String code;
    private String msg;

    ManageExceptionEnum(String code, String msg) {
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
