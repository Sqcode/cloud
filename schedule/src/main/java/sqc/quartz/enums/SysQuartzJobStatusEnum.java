package sqc.quartz.enums;

public enum SysQuartzJobStatusEnum {

    STOPPED("0", "停止"),
    RUNNING("1", "运行"),
    PAUSED("2", "暂停"),
    DELETED("-1", "删除");

    private String value;
    private String code;

    private SysQuartzJobStatusEnum(String code, String value) {
        this.value = value;
        this.code = code;
    }

    public static SysQuartzJobStatusEnum instance(String code) {
        for (SysQuartzJobStatusEnum e : SysQuartzJobStatusEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 获取value
     */
    public String getValue() {
        return value;
    }

    /**
     * 获取code
     */
    public String getCode() {
        return code;
    }

}
