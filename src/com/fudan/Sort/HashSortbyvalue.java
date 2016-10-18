package com.fudan.Sort;

import java.util.*;

/**
 * 根据 HashMap 的 value 进行排序
 * @author Winter Lau
 * @date 2009-11-24 下午01:35:37
 */
public class HashSortbyvalue { 
	public static  LinkedHashMap<String, Float> treeMap_sort(HashMap<String, Float> base_map){
		ByValueComparator bvc = new ByValueComparator(base_map);
		List<String> keys = new ArrayList<String>(base_map.keySet());
		LinkedHashMap<String, Float> sortwordss=new LinkedHashMap<>();
		Collections.sort(keys, bvc);
		for(String key : keys) {
			sortwordss.put(key, base_map.get(key));
			//System.out.printf("%s -> %d\n", key, base_map.get(key));
		}
		return sortwordss;
	}
	static class ByValueComparator implements Comparator<String> {
		HashMap<String, Float> base_map;

		public ByValueComparator(HashMap<String, Float> base_map) {
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
	//第二中排序方法
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
				//System.out.println(entry.getKey()+"->"+ entry.getValue() );
			}  
			return result;  
		} 
}
