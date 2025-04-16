package edu.zjnu;


/**
 * Operator
 *
 * @Date 2025-04-16 15:25
 * @Author 杨海波
 **/
public enum Operator {
    EQ("等于", 1),
    NE("不等于", 2),
    GT("大于", 3),
    LT("小于", 4),
    GE("大于等于", 5),
    LE("小于等于", 6);

    private final String desc;
    private final int code;

    Operator(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public static Operator fromCode(int code) {
        for (Operator op : values()) {
            if (op.code == code) return op;
        }
        throw new IllegalArgumentException("Invalid operator code: " + code);
    }

    public String getDesc() {
        return desc;
    }
}
