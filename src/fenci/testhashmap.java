package fenci;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class testhashmap {
	public static void main(String[] args) {
		HashMap<String, Integer> map1=new HashMap<>();
		HashMap<String, Integer> map2=new HashMap<>();
		HashMap<String, Integer> map3=new HashMap<>();
         
        map1.put("key1", 12);
        map1.put("key2", 432);
        map1.put("key3", 1);
        map2.put("key1", 3);
        map2.put("key2", 5);
        map2.put("key4", 5);
        map3.put("key1", 5);
        map3.put("key3", 5);
        map3.put("key5", 5);
         
        System.out.println(map1.toString());
        //然后通过set1、set2取出每个map的值进行比较就好了
         
}
}
