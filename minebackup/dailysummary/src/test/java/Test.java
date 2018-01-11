    import java.util.*;

    /**
     * @author wqkenqing
     * @emai wqkenqingto@163.com
     * @time 2017/9/27
     * @desc
     */
    public class Test {
        public static void main(String[] args) {
            String str = "a bc de，f,ww g";
            String[] split = str.split(" +|[，,]");
            for (String s : split) {
                System.out.println(s);
            }
        }


    }
