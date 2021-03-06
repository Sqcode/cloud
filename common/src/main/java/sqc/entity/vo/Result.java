package sqc.entity.vo;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import sqc.enums.SystemErrorTypeEnum;
import sqc.exception.BaseException;
import sqc.exception.ErrorType;

import java.time.LocalDateTime;

@ApiModel(description = "Controller请求的返回模型")
@Getter
public class Result<T> {

    public static final String SUCCESSFUL_CODE = "00000";
    public static final String SUCCESSFUL_MSG = "success";

    public static final String LOGIC_CODE = "30000";
    public static final String LOGIC_MSG = "业务异常";

    @ApiModelProperty(value = "结果code", required = true)
    private String code;
    @ApiModelProperty(value = "描述信息")
    private String msg;
    @ApiModelProperty(value = "请求结果生成时间戳")
    private LocalDateTime time;
    @ApiModelProperty(value = "结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result() {
        this.time = LocalDateTime.now();
    }

    /**
     * @param errorType
     */
    public Result(ErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
        this.time = LocalDateTime.now();
    }

    /**
     * @param errorType
     * @param data
     */
    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param msg
     * @param data
     */
    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = LocalDateTime.now();
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result success(Object data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MSG, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static Result fail() {
        return new Result(SystemErrorTypeEnum.B0001);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param baseException
     * @return Result
     */
    public static Result fail(BaseException baseException) {
        return fail(baseException, null);
    }


    /**
     * 业务错误默认返回类型
     * @return
     */
    public static Result logicFail() {
        return new Result<>(LOGIC_CODE, LOGIC_MSG, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(BaseException baseException, Object data) {
        return new Result<>(baseException.getErrorType(), data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static Result fail(ErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    public static Result fail(ErrorType errorType) {
        return Result.fail(errorType, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(Object data) {
        return new Result<>(SystemErrorTypeEnum.B0001, data);
    }


//    /**
//     * 成功code=000000
//     *
//     * @return true/false
//     */
//    @JsonIgnore
//    public boolean isSuccess() {
//        return SUCCESSFUL_CODE.equals(this.code);
//    }
//
//    /**
//     * 失败
//     *
//     * @return true/false
//     */
//    @JsonIgnore
//    public boolean isFail() {
//        return !isSuccess();
//    }
}
