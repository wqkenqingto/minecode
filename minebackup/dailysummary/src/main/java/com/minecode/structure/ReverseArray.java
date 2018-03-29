package com.minecode.structure;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/19
 * @desc
 */
public class ReverseArray {


    public static void main(String[] args) {
        int[] a = {1, 2, 3,4,5};
        for (int i = 0; i < a.length; i++) {
            if (i > (a.length-1)/2) {
                break;
            }
            int temp = a[a.length - i - 1];
            a[a.length - i - 1] = a[i];
            a[i] = temp;
        }
        for (int b : a) {
            System.out.println(b);
        }
    }
}
