package com.minecode.structure.lintcode.pratice;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/26
 * @desc
 */
public class Test {
    //计算数字k在0到n中的出现的次数，k可能是0~9的一个值
    public int digitCounts(int k, int n) {
        int count = 0;
        // write your code here
        for (int i = 0; i <= n; i++) {
            String temp = "";
            temp += i;
            while (temp.contains(k + "")) {
                if (temp.length() == 1) {
                    count++;
                    break;
                } else {
                    temp = temp.substring(temp.indexOf(k + "")+1);
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Test test = new Test();
        int result = test.digitCounts(1, 12);
        System.out.println(result);
    }
}
