package com.xiaoming.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liangyi
 * @Date 2019/12/3
 */
public class Image2Base64Test {

    @Test
    public void translateByRootPath() {
        System.out.println(Image2Base64.translateByRootPath("C:\\Users\\admin\\Desktop\\图片\\仪表盘.png"));
    }
}