package com.minecode.mail;

import com.minecode.day.WeatherNoticeApi;
import com.minecode.util.EmailProduct;
import org.apache.lucene.search.suggest.tst.TSTAutocomplete;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqkenqing on 2017/9/20.
 */
public class Test {
    public static void main(String[] args) throws URISyntaxException {
        Test test = new Test();
        String path = test.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().toString();
        int lastIndexOf = path.lastIndexOf("/");
        path.substring(0, lastIndexOf);
        System.out.println(path);

//        String s = "订单号\t下单时间\t支付时间\t订单状态\t付款方式\t支付状态\t酒店名称\t入住开始时间\t入住结束时间\t房型\t预定天数\t房间数\t间夜量 \t 订单原价 \t 获赠积分 \t 积分抵用金额 \t 优惠券抵 \t 优惠券批次号 \t 支付金额 \t 手续费（收款渠道） \t 实收款 \t 收款方式 \t支付流水号\t供应商名称\t外部订单号\t成本价\t佣金\t联系人\t联系电话\t付款交易模式";
//        StringBuffer sb = new StringBuffer();
//        Arrays.asList(s.split("\t")).forEach(ss-> {
//            sb.append(ss);
//            sb.append(";");
//        });
//        System.out.println(sb);
        LocalDate day = LocalDate.now();
        System.out.println(day);

//        String sql = "select detail.order_no, main.show_status_name,flight.order_time,flight.pay_time,cus.ticket_no,'',\" \\\n" +
//                "            \"case flight.trip_type when 0 then '单程' when 1 then '往返' end as trip_type,cus.name,\" \\\n" +
//                "            \"case cus.adult when 0 then '儿童' when 1 then '成人' end as adult,detail.code,detail.sale_cabin_code,\"\\\n" +
//                "            \"detail.depart_city_name,detail.arr_city_name,'',detail.dpt_date,detail.print_pirce,\"\\\n" +
//                "            \"case cus.adult when 1 then detail.construction_fee when 0 then detail.child_construction_fee end as construction_fee, \" \\\n" +
//                "            \"case cus.adult when 1 then detail.fuel_surcharge when 0 then detail.child_fuel_surcharge end as fuel_surcharge,\"\\\n" +
//                "            \"'','',flight.pnr,main.total_price,'','',main.paid_price,case info.pay_type when 1 then '微信' when 2 then '支付宝' end as pay_type,\" \\\n" +
//                "            \"info.trade_no,main.coupon_price,expand.coupon_batch_id,main.integral_count,main.integral_price,mts.order_no,\" \\\n" +
//                "            \"mts.serv_name,mts_main.show_status_name,(mts.adult_num+mts.child_num) as members,mts.total_price,mts_main.paid_price, detail.third_order_no,'','','','',yi_info.trade_no, \"\\\n" +
//                "            \"ifnull(substring(cus.ticket_no,1,3),0), \"\\\n" +
//                "            \"case cus.adult when 1 then ifnull(other.balance_price,0) when 0 then ifnull(other.child_balance_price,0) end as balance_price,expand.contact_real_name,expand.contact_phone \"\\\n" +
//                "          \"from order_customer_info cus \"\\\n" +
//                "            \"left join order_flight_detail detail on cus.order_sub_no =detail.order_sub_no \"\\\n" +
//                "            \"left join order_flight flight on detail.order_no=flight.order_no \"\\\n" +
//                "            \"left join order_main main  on  detail.order_no=main.order_no \"\\\n" +
//                "            \"left join order_main_expand expand on detail.order_no=expand.order_no \"\\\n" +
//                "            \"left join order_pay_info info on (detail.order_no=info.order_no and info.pay_type in (1,2)) \"\\\n" +
//                "            \"left join order_mts_detail mts on main.order_no =mts.ref_order_no \"\\\n" +
//                "            \"left join order_main as mts_main on mts.order_no =mts_main.order_no \"\\\n" +
//                "            \"left join order_pay_info yi_info on (detail.order_no=yi_info.order_no and yi_info.pay_type =3) \"\\\n" +
//                "            \"left join order_flight_detail as other on (cus.order_sub_no =other.order_sub_no and other.order_source not in ('TUN','ABE'))\"\\\n" +
//                "          \"where flight.pay_time is not null \"\\\n" +
//                "            \"and main.order_type =4 and flight.pay_time>=DATE_FORMAT(date_sub(current_date(),interval 1 day),'%Y-%m-%d 00:00:00') and flight.pay_time<=DATE_FORMAT(date_sub(current_date(),interval 1 day),'%Y-%m-%d 23:59:59') \"\\\n" +
//                "          \"order by main.order_time asc , main.order_no asc;";
//        sql = sql.replace("  ", "");
//        sql = sql.replace("\t", "");
//        sql = sql.replace("\n", "");
//        sql = sql.replace("\\", "");
//        sql = sql.replace("\"", "");
//        System.out.println(sql);

    }
}
