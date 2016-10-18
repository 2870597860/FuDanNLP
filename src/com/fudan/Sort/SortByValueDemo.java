package com.fudan.Sort;

import java.util.*;

/**
 * 根据 HashMap 的 value 进行排序
 * @author Winter Lau
 * @date 2009-11-24 下午01:35:37
 */
public class SortByValueDemo {
 
    public static void main(String[] args) {
         
        HashMap<String, Integer> datas = new HashMap<String, Integer>(){{
            put("Winter Lau", 100);
            put("Aier", 150);
            put("Eothing", 30);
            put("Zolo", 330);
        }};
         
        ByValueComparator bvc = new ByValueComparator(datas);
         
        //第一种方法
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
        sorted_map.putAll(datas);
         
        for(String name : sorted_map.keySet()){
            System.out.printf("%s -> %d\n", name, datas.get(name));
        }
 
        //第二种方法
        List<String> keys = new ArrayList<String>(datas.keySet());
        TreeMap<String, Integer> sort=new TreeMap<>();
        Collections.sort(keys, bvc);
        for(String key : keys) {
        	sort.put(key, datas.get(key));
            System.out.printf("%s -> %d\n", key, datas.get(key));
        }
        
        for (String s : sort.keySet()) {
			System.out.print(s+"->");
			System.out.println(sort.get(s));
		}
        System.out.println("-----------第三种");
        Map<String, Integer> result=sortByValue(datas);
        System.out.println(result);
    }
 
    static class ByValueComparator implements Comparator<String> {
        HashMap<String, Integer> base_map;
 
        public ByValueComparator(HashMap<String, Integer> base_map) {
            this.base_map = base_map;
        }
 
        public int compare(String arg0, String arg1) {
            if (!base_map.containsKey(arg0) || !base_map.containsKey(arg1)) {
                return 0;
            }
 
            if (base_map.get(arg0) < base_map.get(arg1)) {
                return 1;
            } else if (base_map.get(arg0) == base_map.get(arg1)) {
                return 0;
            } else {
                return -1;
            }
        }
    }
    public static <K, V extends Comparable<? super V>> Map<K, V>   sortByValue( Map<K, V> map )  
	{  
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );  
		//LinkedHashMap<String, Float> sortwordss=new LinkedHashMap<>();
		Collections.sort( list, new Comparator<Map.Entry<K, V>>()  
		{  
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )  
			{  
				return (o2.getValue() ).compareTo( o1.getValue());  
			}  
		} );  

		Map<K, V> result = new LinkedHashMap<K, V>();  
		for (Map.Entry<K, V> entry : list)  
		{  
			result.put( entry.getKey(), entry.getValue() ); 
			System.out.println(entry.getKey()+"->"+ entry.getValue() );
		}  
		return result;  
	}  
}
