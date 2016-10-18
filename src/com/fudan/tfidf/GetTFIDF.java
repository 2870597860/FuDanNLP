package com.fudan.tfidf;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fudan.Sort.HashSortbyvalue;


public class GetTFIDF {
	public  Map<String, LinkedHashMap<String, Float>> tfidf(String dir) throws IOException {
		
		Map<String, LinkedHashMap<String, Float>> sorttdidf=new HashMap<String, LinkedHashMap<String,Float>>();
		//获取tf比率
		Map<String, HashMap<String, Float>> tfRate =getTF(dir);
		Map<String, Float> idf = getIDF(dir);//计算idf用到词频（这里是词语个数）
		sorttdidf=gettfidf(idf,tfRate);
		return sorttdidf;
	}
	private static Map<String, HashMap<String, Float>> getTF(String dir){
		Map<String, HashMap<String, Float>> tfRate = new HashMap<String, HashMap<String,Float>>();
		try {
			GetTF.tfRateNumOfAll(dir);
			tfRate=GetTF.getAllTheTfRate();
			System.out.println();
			System.out.println("tf获取结束-----------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tfRate;

	}
	private static Map<String, Float> getIDF(String dir){
		GetIDF getidf=new GetIDF();
		Map<String, Float> idf = new HashMap<>();
		try {
			idf = getidf.idf(dir);//求idf用的是词频（即时词语个数）
			System.out.println();
			System.out.println("idf获取结束-----------");   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idf;

	}
	private static Map<String, LinkedHashMap<String, Float>> gettfidf(Map<String, Float> idf ,Map<String, HashMap<String, Float>> tf){	
		Map<String, LinkedHashMap<String, Float>> sorttdidf=new HashMap<String, LinkedHashMap<String,Float>>();
		for (String file : tf.keySet()) {
			HashMap<String, Float> singelFile = tf.get(file);
			LinkedHashMap<String, Float> sortword=new LinkedHashMap<>();
			for (String word : singelFile.keySet()) {
				singelFile.put(word, (idf.get(word)) * singelFile.get(word));
			}
			sortword= (LinkedHashMap<String, Float>) HashSortbyvalue.sortByValue(singelFile);
			// sortword= HashSortbyvalue.treeMap_sort(singelFile);
			sorttdidf.put(file, sortword);
			System.out.println(file+"排序后的所有的词总数:"+sorttdidf.get(file).size());
		}
		return sorttdidf;
	}
}