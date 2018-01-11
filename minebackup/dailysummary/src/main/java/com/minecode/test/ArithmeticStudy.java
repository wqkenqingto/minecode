package com.minecode.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/11/5
 * @desc
 */
public class ArithmeticStudy {
    public static void maoPao() {
        int[] numbers = {3, 4, 1, 2};
        List<Integer> nlist = new ArrayList<Integer>();

        while (nlist.size() != numbers.length) {
            //将最大值排上去，并在原数基础上不再排列
            int temp = numbers[0];
            for (int i = 0; i < numbers.length - 1; i++) {
                if (nlist.contains(numbers[i])) {
                    if (nlist.contains(temp)) {
                        if (i == numbers.length + 1) {
                            temp = numbers[i];
                        } else {
                            temp = numbers[i + 1];

                        }
                    }
                    continue;
                }
                if (temp < numbers[i]) {
                    temp = numbers[i];
                }

            }
            nlist.add(temp);

        }
        System.out.println(nlist);
    }


}


