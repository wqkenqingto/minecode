package com.minecode.study.thread;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Locale;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/9/25
 * @desc
 */
public class ThreadTest extends Thread {
    @Override
    public void run() {
        LocalTime time = LocalTime.now();
        System.out.println(time);
    }

    public static void main(String[] args) {
        String s = "订单号;下单时间;支付时间;订单状态;付款方式;支付状态;酒店名称;入住开始时间;入住结束时间;房型;预定天数;房间数;间夜量;订单原价;获赠积分;积分抵用金额; 优惠券抵 ; 优惠券批次号 ; 支付金额 ; 手续费（收款渠道） ; 实收款 ; 收款方式 ;支付流水号;供应商名称;外部订单号;成本价;佣金";
        String[] ss = s.split(";");
        StringBuffer sb = new StringBuffer();
        for (String w : ss) {
            sb.append(w.trim());

            sb.append(";");
        }
        System.out.println(sb);
    }
}
