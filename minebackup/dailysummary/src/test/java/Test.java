import java.util.HashMap;
import java.util.Map;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/9/27
 * @desc
 */
public class Test {
    transient  Map map = new HashMap();
    public static void main(String[] args) {
        Map map = new HashMap();
         Map map1 = new HashMap();
        map.put("1", 1);
        map.put("2", 1);
        System.out.println(map);



    }
}
