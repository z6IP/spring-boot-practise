package top.zhz.boot.config.enums;

public enum ResultStatus {
    SUCCESS("成功"),
    FAIL("失败");

    private String label;
    public String getLabel() {
        return label;
    }
    private ResultStatus(String label) {
        this.label = label;
    }
}
