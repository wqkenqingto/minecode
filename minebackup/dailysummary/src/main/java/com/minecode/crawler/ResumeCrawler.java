package com.minecode.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/11/1
 * @desc
 */
public class ResumeCrawler {
    static String username = "17600660686";
    static String password = "125323wq";

    public static void getJob() throws IOException {
//         Document doc = Jsoup.connect("https://login.51job.com/login.php?lang=c").data("loginname", "17600660686", "password", "125323wq", "action", "save", "verifycode", "", "isread", "on","lang","c").
//                 header("Origin","https://login.51job.com")
//                 .header("Upgrade-Insecure-Requests","")
//                 .header("Content-Type","application/x-www-form-urlencoded")
//                 .header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
//                 .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
//                 .header("Referer","https://login.51job.com/login.php?lang=c")
//                 .header("Accept-Encoding","gzip, deflate, br")
//                 .header("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6")
//                 .post();
//        System.out.println(doc);
        Document doc = Jsoup.connect("http://i.51job.com/userset/my_apply.php?lang=c")
                .header("Cookie", "Hm_lvt_9d483e9e48ba1faa0dfceaf6333de846=1508201647,1508949153,1508949257; guid=1509524233310900081; _ujz=MTEyNjg0NTA0MA%3D%3D; ps=us%3DXGVWPA5tAipTMwtuUTwALQc2CjhQZ1MoATZcPw8wD3MKMwBuBmRTbFYyDmBQMVVkADADMVpsBmBcb1R6DFFSOFwxVmsOFg%253D%253D%26%7C%26needv%3D0; slife=resumeguide%3D1%26%7C%26lowbrowser%3Dnot%26%7C%26lastlogindate%3D20171101%26%7C%26; 51job=cuid%3D112684504%26%7C%26cusername%3Dphone_13001038696%26%7C%26cpassword%3D%26%7C%26cname%3D%25CD%25F5%25BF%25FC%25C7%25E5%26%7C%26cemail%3Dwqkenqingto%2540163.com%26%7C%26cemailstatus%3D3%26%7C%26cnickname%3D%26%7C%26ccry%3D.0Mjk2N2saoVU%26%7C%26cconfirmkey%3Dwq5L.1AzcV.bg%26%7C%26cresumeids%3D.05IIF6eNenEM%257C%26%7C%26cautologin%3D1%26%7C%26cenglish%3D0%26%7C%26sex%3D0%26%7C%26cnamekey%3Dwq7Fgw5vZLxQM%26%7C%26to%3DXGVUPg5uAzNWPwFpCmpRYgE2BntaLAA9UzpXawFuVAsNNFE5AWMDNVQ0CWVWMFNoBzJXb1NjVn9VYgBnDDJWZ1xkVDkObQM9Vj4BbQ%253D%253D%26%7C%26")
                .get();
        System.out.println(doc);

    }

    public static void getZhilian() throws IOException {
        Document doc = Jsoup.connect("https://i.zhaopin.com/PositionRecord/jobpostrecord/_JobPostHistory")
                .header("Cookie", "referrerUrl=https%3A//i.zhaopin.com/; stayTimeCookie=1509527887110; LastCity=%e5%8c%97%e4%ba%ac; LastCity%5Fid=530; JSSearchModel=0; dywez=95841923.1509268239.4.3.dywecsr=my.zhaopin.com|dyweccn=(referral)|dywecmd=referral|dywectr=undefined|dywecct=/jobseeker/req_vacancy_ok.asp; zg_did=%7B%22did%22%3A%20%2215f6b4e0f8d3f-014744eca2e927-31657c00-1fa400-15f6b4e0f8e1c8%22%7D; zg_08c5bcee6e9a4c0594a5d34b79b9622a=%7B%22sid%22%3A%201509333798806%2C%22updated%22%3A%201509333799152%2C%22info%22%3A%201509333798815%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22sou.zhaopin.com%22%7D; dywem=95841923.y; JSloginnamecookie=17600660686; at=b66977eff242484caf29fbd3efbd9909; rt=44f3947828f04596ae35149897cd17e3; JSpUserInfo=3D692E6956714171527759755C6A547549775A695A695F714C7129772775546A557547775E695A695B714171537759755F6A5C75467753693F6925714A71EE04272C6C045F753577256957691C7106710E770C75076A1575197706690D691F711871257758755F6A57755E770B69056906714C7136773D75546A55754B7729693E695671467154774475586A447549775269596959714C7124772575546A55754B773D692B6956713D7128775E75596A51754077516958695B714371547752753C6A30754D775969516938713E7158775875526A31752077266957691C7106710E770C75076A1575197706690D691F711871257758755F6A57755E770B69056906714C716; uiioit=3B622A6459640E644064476A5D6E526E5D6456385477507751682C622A64596408644C646; urlfrom2=121126445; adfcid2=none; adfbid2=0; JsNewlogin=2011527614; monitorlogin=Y; loginreleased=1; resumeTopFlag=1; LastSearchHistory=%7b%22Id%22%3a%22eea6ef41-71c8-4b44-bab6-db8e691812d2%22%2c%22Name%22%3a%22%e5%a4%a7%e6%95%b0%e6%8d%ae%e5%bc%80%e5%8f%91%e5%b7%a5%e7%a8%8b%e5%b8%88+%2b+%e5%8c%97%e4%ba%ac+%2b+10001-150...%22%2c%22SearchUrl%22%3a%22http%3a%2f%2fsou.zhaopin.com%2fjobs%2fsearchresult.ashx%3fjl%3d%25e5%258c%2597%25e4%25ba%25ac%26kw%3d%25e5%25a4%25a7%25e6%2595%25b0%25e6%258d%25ae%25e5%25bc%2580%25e5%258f%2591%25e5%25b7%25a5%25e7%25a8%258b%25e5%25b8%2588%26isadv%3d0%26isfilter%3d1%26sf%3d10001%26st%3d15000%26sg%3dd4d1dd8d7370405e9ea71081214822ce%26p%3d2%22%2c%22SaveTime%22%3a%22%5c%2fDate(1509526526898%2b0800)%5c%2f%22%7d; JSREQVACANCY=047%7c530; pt670509204=3%7c2017-11-03+16%3a53%3a41; firstchannelurl=https%3A//my.zhaopin.com/jobseeker/req_vacancy_ok.asp%3FVanMsg%3DCC443705682J90250174000_530_1_03_201_1_3_%26resid%3DJM705092041R90250001000_1%26covid%3D%26source%3D%26f%3D%26r%3Dr%26jl%3D530%26jt%3D%25e5%25a4%25a7%25e6%2595%25b0%25e6%258d%25ae%25e5%25bc%2580%25e5%258f%2591%25e5%25b7%25a5%25e7%25a8%258b%25e5%25b8%2588%26subjobtype%3D047%257C530%26jdr%3D%26ref%3Dhttp%253A%252F%252Fjobs.zhaopin.com%252F443705682250174.htm%26applyType%3D0; lastchannelurl=http%3A//jobs.zhaopin.com/443705682250174.htm%3Fssidkey%3Dy%26ss%3D201%26ff%3D03%26sg%3Dd4d1dd8d7370405e9ea71081214822ce%26so%3D23%26uid%3D670509204; usermob=44755E695F654777487843755F6959654177407843752; userphoto=%2fpic%2f2016%2f6%2f19%2f13632c46-127b-47e0-938e-1fb01242f566.jpg; userwork=2; bindmob=2; JSShowname=%e7%8e%8b%e5%a5%8e%e6%b8%85; rinfo=JM705092041R90250001000_1; __utmt=1; stayTimeCookie=1509527881746; referrerUrl=https%3A//i.zhaopin.com/; nTalk_CACHE_DATA={uid:kf_9051_ISME9754_670509204,tid:1509526132329387}; NTKF_T2D_CLIENTID=guestDAEE77EE-E785-16B2-F3A7-66E02B9BA8B3; Hm_lvt_38ba284938d5eddca645bb5e02a02006=1509327304,1509345474,1509412015,1509526132; Hm_lpvt_38ba284938d5eddca645bb5e02a02006=1509527887; dywea=95841923.1757629244532804400.1509196791.1509518544.1509526132.25; dywec=95841923; dyweb=95841923.36.9.1509527885957; __utma=269921210.43706628.1509196791.1509518544.1509526132.25; __utmb=269921210.36.9.1509527885960; __utmc=269921210; __utmz=269921210.1509268239.4.3.utmcsr=my.zhaopin.com|utmccn=(referral)|utmcmd=referral|utmcct=/jobseeker/req_vacancy_ok.asp; __utmv=269921210.|2=Member=2011527614=1")
                .data("pageNum", "2")
                .post();
        System.out.println(doc);
    }

    public static void main(String[] args) throws IOException {
        getZhilian();

    }
}
