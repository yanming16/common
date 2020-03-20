package com.xiaoming.tmp;

import org.junit.Test;

/**
 * @author liangyi
 * @Date 2020/3/5
 */
public class FormatString {

    private static final String FORMAT = "%s_%s";

    @Test
    public void test1() {
        System.out.println(buildReportId("ss", "tt"));
    }

    private String buildReportId(final String source, final String obsDesignId) {
        return String.format(FORMAT, obsDesignId, source);
    }
}
