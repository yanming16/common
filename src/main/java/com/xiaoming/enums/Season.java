package com.xiaoming.enums;

/**
 * @author liangyi
 * @Date 2019/12/6
 */
public enum Season {
    SPRING("春天"), SUMMER("夏天"), AUTUMN("秋天"), WINTER("冬天");

    private final String chinese;

    private Season(String chinese) {
        this.chinese = chinese;
    }

    public String getChinese() {
        return chinese;
    }

}