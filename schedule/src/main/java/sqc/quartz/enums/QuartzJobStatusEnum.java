package sqc.quartz.enums;

public enum QuartzJobStatusEnum {

    STOPPED("0", "停止"),
    RUNNING("1", "运行"),
    PAUSED("2", "暂停");

    private String value;
    private String code;

    private QuartzJobStatusEnum(String code, String value) {
        this.value = value;
        this.code = code;
    }

    public static QuartzJobStatusEnum instance(String code) {
        for (QuartzJobStatusEnum e : QuartzJobStatusEnum.values()) {
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
