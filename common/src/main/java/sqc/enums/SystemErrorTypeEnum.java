package sqc.enums;

import sqc.exception.ErrorType;

/**
 * @Description: 系统执行出错
 * @author: Sqcode
 * @since: 2021/5/21 10:27
 */
public enum SystemErrorTypeEnum implements ErrorType {

    B0001("B0001", "系统执行出错");
//    - B0001 系统执行出错 一级宏观错误码
//    B0100 系统执行超时 二级宏观错误码
//    B0101 系统订单处理超时
//    B0200 系统容灾功能被触发 二级宏观错误码
//    B0210 系统限流
//    B0220 系统功能降级
//    B0300 系统资源异常 二级宏观错误码
//    B0310 系统资源耗尽
//    B0311 系统磁盘空间耗尽
//    B0312 系统内存耗尽
//    B0313 文件句柄耗尽
//    B0314 系统连接池耗尽
//    B0315 系统线程池耗尽
//    B0320 系统资源访问异常
//    B0321 系统读取磁盘文件失败

    private String code;

    private String msg;

    SystemErrorTypeEnum(String code, String msg) {
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
