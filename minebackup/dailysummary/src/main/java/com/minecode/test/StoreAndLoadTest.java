package com.minecode.test;

import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/11/7
 * @desc:分析题意数组元素为map，每个作为文本字符串的一行
 */
public class StoreAndLoadTest {
    static String common = "\n";
    static String common2 = "=";
    protected final static Log logger = LogFactory.getLog(StoreAndLoadTest.class);

    public static String store(Map<String, String>[] maps) {
        StringBuffer sb = new StringBuffer();
        for (Map<String, String> m : maps) {
            Iterator iterator = m.keySet().iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next() + common2 + m.get(iterator.next()));
                sb.append(";");
            }
            sb.append(common);
        }
        return sb.toString();
    }

    public static Map<String, String>[] load(String text) {
        String[] rows = text.split(common);
        Map<String, String> rowMap = null;
        Map<String, String>[] maps = new HashMap[rows.length];
        int index = 0;
        for (String row : rows) {
            if (index >= rows.length) {
                logger.error("下标越界");
                break;
            }
            if (row.contains(";")) {
                String[] rr = row.split(";");
                for (String r : rr) {
                    rowMap = new HashMap<>();
                    String key = r.split("=")[0];
                    String val = r.split("=")[1];
                    rowMap.put(key, val);
                }
            } else {
                String key = row.split("=")[0];
                String val = row.split("=")[1];
                rowMap.put(key, val);
            }
            maps[index] = rowMap;
            index++;
        }
        return maps;
    }
}
