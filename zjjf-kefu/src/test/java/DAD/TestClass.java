package DAD;

import org.jboss.netty.util.internal.ConcurrentHashMap;

public class TestClass {
    private ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
    //StringBuffer
    public void add(String key){
        Integer value = map.get(key);
        if(value==null){
           map.put(key,1);
        }else{
            map.put(key,value+1);
        }
    }
}
