package com.minecode.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/12/19
 * @desc
 */
public class StreamTest {
    public static void main(String[] args) {
        args = new String[]{"ab", "bc", "de"};
        List<String> alist = Arrays.asList(args);

        List<String> nlist = alist.stream().map(s -> {
            int a = (int) (Math.random() * 10);
            s = s + a;
            return s;
        }).filter(s -> s.contains("c")).collect(Collectors.toList());
        System.out.println(alist);
        System.out.println(nlist);

    }
}
