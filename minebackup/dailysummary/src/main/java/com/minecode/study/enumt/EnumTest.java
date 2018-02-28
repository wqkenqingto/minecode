package com.minecode.study.enumt;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/9/22
 * @desc
 */
public enum EnumTest {
    MAN(0, "男"), WOMEN(1, "女");

    private final int value;
    private final String name;

    EnumTest(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
