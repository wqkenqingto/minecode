package com.minecode.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minecode.util.CommonUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by wqkenqing on 2017/9/15.
 */
public class IpAddressApi {
    public static void main(String[] args) throws IOException {
        String inpath = "/Users/wqkenqing/Desktop/ipfiles";
        String outpath = "/Users/wqkenqing/Desktop/ipout/";
        InputStream in = new FileInputStream(inpath);
        String ips = CommonUtil.stream2String(in, "utf8");
        String[] ipes = ips.split("\n");
        StringBuffer sb = new StringBuffer();
        for (String ip : ipes) {
//            String url = "http://ip.taobao.com/service/getIpInfo.php?ip=";
            String url = "http://opendata.baidu.com/api.php?query=" + ip;
            url += "&co=&resource_id=6006&t=1433920989928&ie=utf8&oe=gbk&format=json";
            System.out.println("开始处理" + ip + "...");
//            url = url + ip;
            System.out.println(url);

            try {
//                String ipinfo = getResult(url, ip, "utf8");
                String ipinfo = Jsoup.connect(url).ignoreContentType(true)
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Encoding", "gzip, deflate, sdch")
                        .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                        .header("Cache-Control", "max-age=0")
                        .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                        .header("Cookie", "hng=CN%7Czh-CN%7CCNY%7C156; thw=cn; uc3=sg2=WvmNICdDsVVGzqaSwFzkC2gfPVJC2nSMSVFfyUHgMKU%3D&nk2=FOgCafaUXHyc&id2=UoH7IkzU6XLMMw%3D%3D&vt3=F8dBzWfQdLsyygx6P4E%3D&lg2=Vq8l%2BKCLz3%2F65A%3D%3D; uss=AVMBl66gMvGglu%2FrtZTjVutPQIbpF3sI%2F6lG0%2FA3O84Sgfuw5kQcVMPiAw%3D%3D; lgc=wqkenqing; tracknick=wqkenqing; mt=np=; t=899dec8a9c8af480a856d0244b1822e3; _cc_=VT5L2FSpdA%3D%3D; tg=0; cna=ghwUEmzBc1kCAXaQhSeBYv2b; miid=1979176006272289225; isg=As_PEmzvynHaOM43_4h-VOx7XmUZXCI0ZhRmlOHcdj5FsO6y6MYiZ6dgxNf0; cookie2=10a2c05534b695a7111b233035f0d70c; _tb_token_=43b385535377; v=0")
                        .header("Host", "ip.taobao.com")
                        .get().text();
                JSONObject json = JSONObject.parseObject(ipinfo);
                JSONArray jd = json.getJSONArray("data");
                JSONObject js = jd.getJSONObject(0);
                String location= (String) js.get("location");
//                String isp = (String) jd.get("isp");
                sb.append(ip);
                sb.append("\t");
                sb.append(location);
                sb.append("\n");
            } catch (Exception e) {
                System.out.println(ip + "查询失败.....");
                continue;

            }

        }
        CommonUtil.outputHtml(sb.toString(), "ipinfo", outpath);


    }

    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }
}
