package com.minecode.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.minecode.mail.Test;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: MRExcelUtil
 * @author: wqkenqing
 * @date: 2017/12/15 下午2:36
 * @describe:
 **/
public class ExcelUtil {
    static Logger log = LoggerFactory.getLogger(ExcelUtil.class);
    private static InputStream in;

    //实现效果 1.提前生成好相应的sheet与各表头,2.会传入各raw的col,要作好接收前的所有准备
    static HSSFWorkbook hwb;
    static SXSSFWorkbook swb = new SXSSFWorkbook(10);
    ;
    static HSSFSheet sheet;
    static Sheet ssheet;
    static String splitTag = ";";
    static HSSFCellStyle style;
    static CellStyle sstyle;

    //    static String header = "订单号;订单状态;下单时间;支付时间;票号;客票状态;航程;旅客姓名;乘客类型;航班号;舱位;始发站;目的站;出票日期;乘机日期;票面价;机建;燃油;保险;配送费;PNR;订单金额;手续费;手续费率;实收金额 ;收款方式;支付流水号;优惠券抵;优惠券批次号;获赠积分;积分抵用金额;Hi服务订单号;Hi服务名称;Hi服务订单状态;Hi服务预订人数;Hi服务订单总额;Hi服务实付金额;外部单号;客服人员;备注;采购成本;采购渠道;易生支付流水号";

    static String order_no = "";

    public static HSSFWorkbook createSheetAndheader(String sheetname, String header) {
        String[] headers;
        if (StringUtils.isBlank(header) || StringUtils.isEmpty(header)) {
            headers = new String[]{""};
        } else {
            headers = header.split(splitTag);
        }

        //创建sheet与表头
        if (hwb == null) {
            hwb = new HSSFWorkbook();
        }

        sheet = hwb.createSheet(sheetname);
        HSSFRow row = sheet.createRow((int) 0);
        style = hwb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
        return hwb;
    }


    public static HSSFWorkbook createRowofSheet(String sheetname, String row) {
        sheet = hwb.getSheet(sheetname);
        HSSFRow rown = sheet.createRow(sheet.getLastRowNum() + 1);
        int i = 0;
        for (String r : row.split(splitTag)) {
            if (r.equals("") || r.equals("null") || StringUtils.isEmpty(r) || StringUtils.isBlank(r)) {
                r = "";
            }
            HSSFCell cell = rown.createCell(i);
//            r= new CellReference(r).formatAsString();
            cell.setCellValue(r);
            cell.setCellStyle(style);
            i++;
        }
        Row frow = sheet.getRow(sheet.getFirstRowNum());
        int num = frow.getLastCellNum();
        for (int j = 0; j < num; j++) {
            sheet.autoSizeColumn((short) j);
        }
        return hwb;
    }

    public static HSSFWorkbook createRowofSheet(String sheetname, String row, String tag) {
        sheet = hwb.getSheet(sheetname);
        int i = 0;
        String[] rows = row.split(splitTag);
        String flag = "";
        if (tag.equals("coupon")) {
            if (order_no.equals(rows[0])) {
                return hwb;
            } else {
                order_no = rows[0];
            }
        }
        HSSFRow rown = sheet.createRow(sheet.getLastRowNum() + 1);
        for (String r : rows) {
            if (flag.equals("break")) {
                break;
            }
            if (r.equals("") || r.equals("null") || StringUtils.isEmpty(r) || StringUtils.isBlank(r)) {
                r = "";
            }
            HSSFCell cell = rown.createCell(i);
            cell.setCellValue(r);
            cell.setCellStyle(style);
            i++;
        }
        Row frow = sheet.getRow(sheet.getFirstRowNum());
        int num = frow.getLastCellNum();

        if (tag.equals("flight")) {
            if (order_no.equals(rows[0])) {
                System.out.println(rows[0]);
                sheet.addMergedRegion(new CellRangeAddress(rown.getRowNum() - 1, rown.getRowNum(), 0, 0));
                sheet.addMergedRegion(new CellRangeAddress(rown.getRowNum() - 1, rown.getRowNum(), 21, 21));
                sheet.addMergedRegion(new CellRangeAddress(rown.getRowNum() - 1, rown.getRowNum(), 24, 24));
                sheet.addMergedRegion(new CellRangeAddress(rown.getRowNum() - 1, rown.getRowNum(), 27, 27));
                sheet.addMergedRegion(new CellRangeAddress(rown.getRowNum() - 1, rown.getRowNum(), 28, 28));
                sheet.addMergedRegion(new CellRangeAddress(rown.getRowNum() - 1, rown.getRowNum(), 43, 43));
                sheet.addMergedRegion(new CellRangeAddress(rown.getRowNum() - 1, rown.getRowNum(), 44, 44));
            } else {
                order_no = rows[0];
            }
        }
        for (int j = 0; j < num; j++) {
            sheet.autoSizeColumn((short) j);
        }
        return hwb;
    }


    public static String createExcel(String filename) throws IOException {
        String outpath = "";
        String suffix = ".xls";
        Test test = new Test();
        try {
            if (StringUtils.isBlank(outpath) || StringUtils.isEmpty(outpath)) {
//               + "/excel/";
                String path = test.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().toString();
                int lastIndexOf = path.lastIndexOf("/");
                path = path.substring(0, lastIndexOf);
                outpath = path + "/excel/";
                System.out.println(outpath);
                log.info("excel输出路径为" + outpath);
            }
        } catch (Exception e) {
            log.error("获取jar所处路径异常");
        }

//        outpath = "/Users/wqkenqing/Desktop/bi/excel/" + filename + suffix;
        outpath = outpath + filename + suffix;

        OutputStream out = new FileOutputStream(outpath);
        hwb.write(out);
        log.info("报表输出完成");
        return outpath;
    }

    public static String createExcelS(String filename) throws IOException {
        String outpath = "";
        String suffix = ".xls";
        String outfile = "/Users/wqkenqing/Desktop/bi/excel/" + suffix;

        OutputStream out = new FileOutputStream(outfile);
        swb.write(out);
        log.info("报表输出完成");
        return outfile;
    }


    /**
     * 该方法针对积分生成需求定制
     *
     * @param
     * @return
     * @throws
     * @author kuiqing.wang@hnair
     * @date 2017/12/21
     **/


    public static Integer checkNum(Map<String, Integer> map, String key) {
        if (map.get(key) == null) {
            return 0;
        }
        return map.get(key);
    }

    public static Map<String, Integer> checkNumMap(Map<String, Integer> map, String key) {
        if (map.get(key) == null) {
            map.put(key, 0);
        }
        return map;
    }

    public static String getExcelHead(String header) {

        String basepath = "/excelhead/";
        String path = basepath + header;
        in = DBUtil.class.getResourceAsStream(path);
        String ehead = CommonUtil.stream2String(in, "utf-8");
        return ehead;
    }

}
