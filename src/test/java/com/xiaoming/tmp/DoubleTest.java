package com.xiaoming.tmp;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author liangyi
 * @Date 2019/12/24
 */
public class DoubleTest {

    @Test
    public void test1() {
        String quantity = "2.345435";
        final int quantityInt = NumberUtils.toInt(quantity, -1);
        //工程量
        if (quantityInt != -1) {
            System.out.println(quantityInt);
        }else {
            System.out.println(BigDecimal.valueOf(Double.parseDouble(quantity))
                    .setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
    }
}
