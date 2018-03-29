package com.minecode.structure;

import org.junit.Test;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/20
 * @desc
 */
public class ReverseString {
    String s = "abc123def456";
//    String res = "456fed123cba";
    String res = "12435sdjahk12123ss";
    String res1 = "2121s11112435sdjahk12123ss";


    @Test
    public void test() {
        char[] ss =res1.toCharArray();
        String s = "";
        String n = "";
        for (int i = 0; i < ss.length; i++) {
            if (48 <= (int) ss[ss.length-1-i] && ss[ss.length-1-i] <= 57) {
                n = ss[ss.length-1-i] + n;
            } else if (!n .equals("") ) {
                s = s  + n+ss[ss.length-i-1];
                n = "";
            }else {
                s = s + ss[ss.length - i - 1];
            }
        }
        if (!n.equals("")) {
            s = s+n;
        }
        System.out.println(s);
    }

}
