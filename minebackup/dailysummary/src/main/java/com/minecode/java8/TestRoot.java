package com.minecode.java8;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.poi.util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/12/20
 * @desc
 */
public class TestRoot {
    static String content = " 80099, 16114, 63108, 25032, 31044, 59069, 39099, 13110, 34101, 66120,\n" +
            "                19116, 72105, 70045, 38032, 41110, 12105, 75110, 27105, 1105, 9114,\n" +
            "                67117, 20101, 21100, 11032, 79046, 32112, 5111, 6117, 45116, 22032,\n" +
            "                61097, 65120, 49110, 15101, 82109, 50103, 54110, 17101, 46032, 4121,\n" +
            "                76097, 7032, 57105, 2102, 58044, 8097, 44099, 73064, 81111, 43097,\n" +
            "                30112, 14116, 60109, 74104, 77105, 35097, 64058, 29112, 55032, 33108,\n" +
            "                71108, 40111, 47088, 52117, 56076, 68097, 37101, 78114, 24110, 53097,\n" +
            "                69110, 48105, 18115, 26072, 3032, 42116, 62105, 51120, 28065, 10101,\n" +
            "                23105, 36115\n";

    public static void test() {

        List<String> nlist = Arrays.asList(content.split(",")).stream().map(s -> {
            s = s.trim();
            int len = s.length();
            if (len > 3) {
                int start = len - 3;
                s = s.substring(start);
            }
            return s;
        }).collect(Collectors.toList());

        nlist = nlist.stream().map(s -> {
            if (!StringUtils.isBlank(s)) {
                int a;
                if (s.startsWith("0")) {
                     a = Integer.valueOf(s.substring(s.indexOf(0) + 1));
                    char c = (char) a;
                    s = c + "";
                } else {
                    a = Integer.valueOf(s);
                    char c = (char) a;
                    s = c + "";
                }
            }
            return s;
        }).collect(Collectors.toList());
        for (String s : nlist) {
            System.out.println(s);

        }

    }

    public static void main(String[] args) {
        test();

    }
}
