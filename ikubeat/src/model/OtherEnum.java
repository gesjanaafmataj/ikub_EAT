package model;

public enum OtherEnum {
    APPROVED("ky order u aprovua"),
    REJECTED("ky order nuk u pranua");

    private String value;

    OtherEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
