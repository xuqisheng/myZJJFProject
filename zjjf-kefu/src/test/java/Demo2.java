import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo2
 * @Description:
 * @author: 杨开泰
 * @date: 2016年 10月27 12:33
 */
public class Demo2 {
     public static void  main(String[] args){
        Map<BigDecimal,String> map  = new HashMap<>();
         map.put(new BigDecimal("100"),"100");
         map.put(new BigDecimal("100"),"100");
         System.out.println(map.containsKey("100"));
     }
}
