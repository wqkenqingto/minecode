package com.minecode.test;

import com.minecode.util.CommonUtil;
import com.minecode.util.HbaseUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wqkenqing on 2017/9/1.
 */
public class XmlTest {

    public static void main(String[] args) throws Exception {
        String path = "/Users/wqkenqing/Desktop/backup/city.xml";
        InputStream in = new FileInputStream(path);
        String s = CommonUtil.stream2String(in, "utf8");
        Document doc = DocumentHelper.parseText(s);

        XPath xpathSelector = DocumentHelper.createXPath("//d");
        List nodes = xpathSelector.selectNodes(doc);


        int a = 1;
        List<Put> plist = new ArrayList<Put>();
        StringBuffer sb = new StringBuffer();

        for (Object obj : nodes) {
            a++;
            Element n = (Element) obj;
            String d1 = n.attribute("d1").getText();
            String d2 = n.attribute("d2").getText();
            String d3 = n.attribute("d3").getText();
            String d4 = n.attribute("d4").getText();
            sb.append(d1);
//            sb.append("\t");
//            sb.append(d2);
//            sb.append("\t");
//            sb.append(d3);
//            sb.append("\t");
//            sb.append(d4);
//            sb.append("\n");
            Put put = new Put(Bytes.toBytes(d2));
            put.add(Bytes.toBytes("info"), Bytes.toBytes("id"), Bytes.toBytes(d1));
            put.add(Bytes.toBytes("info"), Bytes.toBytes("province"), Bytes.toBytes(d4));
            put.add(Bytes.toBytes("info"), Bytes.toBytes("py"), Bytes.toBytes(d3));
            plist.add(put);
        }
//        System.out.println(sb.toString());
        System.out.println("将有" + a + "条数据将被上传至hbase");
        System.out.println("-------------------");
        System.out.println("开始上传....");
        HbaseUtil.addPutList("city_id_list",plist);
        System.out.println("上传完成.....");
    }
}
