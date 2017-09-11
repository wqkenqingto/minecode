package com.minecode.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by wqkenqing on 2017/9/1.
 */
public class HbaseUtil {
    // 声明静态配置
    private static Configuration conf = null;
    static StringBuffer sb = new StringBuffer();
    static HTable htable;
    static Connection con;

    static {
        //sql初始化
//
        //--------
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "colony1,colony2,colony3,colony4");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("fs.default.name", "hdfs://colonyware:8020/hbase");
        conf.set("dfs.nameservices", "colonyware");
//        conf.set("dfs.ha.namenodes.colonyware", "colony1,colony2");
//        conf.set("dfs.namenode.rpc-address.colonyware.nn1", "colony1:8020");
//        conf.set("dfs.namenode.rpc-address.colonyware.nn2", "colony2:8020");
        conf.set("dfs.client.failover.proxy.provider.colonyware", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        conf.set("hadoop.home.dir", "/usr/local/hadoop/hadoop");
    }

    //判断表是否存在
    public static boolean isExist(String tableName) throws IOException {
        HBaseAdmin hAdmin = new HBaseAdmin(conf);
        return hAdmin.tableExists(tableName);
    }

    // 创建hbase
    public static void createTable(String tableName, String[] columnFamilys) throws Exception {
        // 新建一个数据库管理员
        HBaseAdmin hAdmin = new HBaseAdmin(conf);
        if (hAdmin.tableExists(tableName)) {
            System.out.println("表 " + tableName + " 已存在！");
            System.exit(0);
        } else {
            // 新建一个students表的描述
            HTableDescriptor tableDesc = new HTableDescriptor(tableName);
//            HTableDescriptor tableDesc=hAdmin
            // 在描述里添加列族
            for (String columnFamily : columnFamilys) {
                tableDesc.addFamily(new HColumnDescriptor(columnFamily.getBytes()));
            }
            // 根据配置好的描述建表
            hAdmin.createTable(tableDesc);
            System.out.println("创建表 " + tableName + " 成功!");
        }
    }

    // 删除数据库表
    public static void deleteTable(String tableName) throws Exception {
        // 新建一个数据库管理员
        HBaseAdmin hAdmin = new HBaseAdmin(conf);
        if (hAdmin.tableExists(tableName)) {
            // 关闭一个表
            hAdmin.disableTable(tableName);
            hAdmin.deleteTable(tableName);
            System.out.println("删除表 " + tableName + " 成功！");
        } else {
            System.out.println("删除的表 " + tableName + " 不存在！");
            System.exit(0);
        }
    }

    // 添加一条数据
    public static void addRow(String tableName, String row, String columnFamily, String column, String value) throws Exception {
        HTable table = new HTable(conf, tableName);
        Put put = new Put(Bytes.toBytes(row));// 指定行
        // 参数分别:列族、列、值
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column),
                Bytes.toBytes(value));
        table.put(put);
    }  // 添加多条数据

    public static void addMoreRow(String tableName, String row, String columnFamily, String column, String value) throws Exception {
        HTable table = new HTable(conf, tableName);
        Put put = new Put(Bytes.toBytes(row));// 指定行
        // 参数分别:列族、列、值
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column),
                Bytes.toBytes(value));
        table.put(put);
    }

    public static void addPutList(String tableName, List<Put> putList) throws Exception {
        HTable table = new HTable(conf, tableName);
        int a = 1;
        for (Put p : putList) {
            a++;
            if (a % 1000 == 0) {
                System.out.println("上传了" + a + "条数据");
            }
            table.put(p);
        }
    }

    public static void checkrow(String outpath, String inpath) throws IOException {
        InputStream in = new FileInputStream(inpath);
        String phones = CommonUtil.stream2String(in, "utf8");
        String[] pes = phones.split("\n");
        int i = 1;
        for (String p : pes) {
            Get get = new Get(Bytes.toBytes(p));
            Result result = htable.get(get);
            i++;
            System.out.println("正在验证第" + i + "个手机号....");
            if (!result.toString().equals("keyvalues=NONE")) {
                System.out.println(p + "匹配成功....");
                sb.append(p);
                sb.append("\n");
            }
        }
        System.out.println("匹配了" + sb.toString().split("\n").length + "个手机号");
        CommonUtil.outputHtml(sb.toString(), "phoneE", outpath);
        System.out.println("验证结果已输出");
    }

    // 获取一条数据
    public static void getRowShow(String tableName, String row) throws Exception {
        HTable table = new HTable(conf, tableName);
        Get get = new Get(Bytes.toBytes(row));
        Result result = table.get(get);
        // 输出结果,raw方法返回所有keyvalue数组
        for (KeyValue rowKV : result.raw()) {
            System.out.print("行名:" + new String(rowKV.getRow()) + " ");
            System.out.print("时间戳:" + rowKV.getTimestamp() + " ");
            System.out.print("列族名:" + new String(rowKV.getFamily()) + " ");
            System.out.print("列名:" + new String(rowKV.getQualifier()) + " ");
            System.out.println("值:" + new String(rowKV.getValue()));
        }
    } // 获取一条数据

    public static Result getRow(String tableName, String row) throws Exception {
        HTable table = new HTable(conf, tableName);
        Result result = null;
        try {
            Get get = new Get(Bytes.toBytes(row));
            result = table.get(get);
        } catch (Exception e) {
            System.out.println("该key不存在");
        }
        return result;
    }

    // 获取所有数据
    public static ResultScanner getAllRows(String tableName) throws Exception {
        HTable table = new HTable(conf, tableName);
        Scan scan = new Scan();
        ResultScanner results = table.getScanner(scan);
        return results;
    }

    public static void getAllRowsShow(String tableName) throws Exception {
        HTable table = new HTable(conf, tableName);
        Scan scan = new Scan();
        ResultScanner results = table.getScanner(scan);
        // 输出结果
        for (Result result : results) {
            for (KeyValue rowKV : result.raw()) {
                System.out.print("行名:" + new String(rowKV.getRow()) + " ");
                System.out.print("时间戳:" + rowKV.getTimestamp() + " ");
                System.out.print("列族名:" + new String(rowKV.getFamily()) + " ");
                System.out.print("列名:" + new String(rowKV.getQualifier()) + " ");
                System.out.println("值:" + new String(rowKV.getValue()));
            }
        }
    }

    // 删除一条(行)数据
    public static void delRow(String tableName, String row) throws Exception {
        HTable table = new HTable(conf, tableName);
        Delete del = new Delete(Bytes.toBytes(row));
        table.delete(del);
    }

    public static void getRowsByNumber(String tname) throws IOException {
//        htable = new HTable(conf, tname);
//        htable.getStartKeys();
//        System.out.println(s);
    }

    public static String getInfoFromRow(Result result, String need) {
        KeyValue[] keyValues = result.raw();
//        result.get''
//        for (KeyValue kv : keyValues) {
//            kv.
//        }
        return "";
    }


    public static ResultScanner getPage(String tname, String start, String end) throws IOException {
        htable = new HTable(conf, tname);
        Scan scan = new Scan();
        scan.setStartRow(start.getBytes());
        scan.setStopRow(end.getBytes());
        ResultScanner results = htable.getScanner(scan);
        return results;
    }

    //获取从Mysql中获取hbase表中的分页索引
    public static String getIndexFromMysql(String tname, String endkey) throws SQLException {
        int limit = 11;
        Statement st = con.createStatement();
        String sql = "";

        if (endkey.equals("0")) {
            sql = "select min(rowkey)start ,max(rowkey)end from ( select rowkey from " + tname + "   where rowkey >" + endkey + "  ORDER BY  rowkey  desc limit 11" + ")travel";
        } else {
//         sql = "select min(rowkey)start ,max(rowkey)end from ( select rowkey from "+tname+"   where rowkey >";
            sql = "select min(rowkey)start ,max(rowkey)end from ( select rowkey from " + tname + "   where rowkey <";
            String sufix = "  ORDER BY rowkey desc limit ";
            String sufix1 = ")travel";
            sql = sql + endkey + sufix + limit + sufix1;
        }

        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        String startend = "";
        while (rs.next()) {
            String start = rs.getString("start");
            String end = rs.getString("end");
            startend = start + ";" + end;
        }

        System.out.println(startend);
        return startend;
    }


    public static void main(String[] args) throws Exception {
//        getRowsByNumber("travel_news_show");
        args = new String[]{"info"};
        createTable("city_id_list", args);
        boolean b = isExist("city_id_list");
        System.out.println(b);
    }
}
