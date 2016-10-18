package com.fudan.tfidf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fudan.preprocessing.MyCWSTagger;
import com.fudan.preprocessing.ReadFiles;

/**
 * 计算IDF
 * @author Administrator
 *
 */
public class GetIDF {
	//获取filelist所有文件集合
	private static List<String> fileList=GetTF.getFileList();
	private static MyCWSTagger myCWSTagger=new MyCWSTagger();
	/**
	 * 
	 * @param 文件夹路径 
	 * @return	返回集合，词频即每个此在每篇文档中
	 * @throws IOException
	 */
	public  Map<String, Float> idf(String dir) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		//公式IDF＝log((1+|D|)/|Dt|)，其中|D|表示文档总数，|Dt|表示包含关键词t的文档数量。
		HashMap<String, HashMap<String, Integer>> allTheNormalTF = new HashMap<String, HashMap<String, Integer>>();
		Map<String, Float> idf = new HashMap<String, Float>();
		List<String> located = new ArrayList<String>();
		float Dt = 1;
		allTheNormalTF=GetTF.getAllTheNormal_TF();
		float D = allTheNormalTF.size();//文档总数
		List<String> key = fileList;//存储各个文档名的List
		Map<String, HashMap<String, Integer>> tfInIdf = allTheNormalTF;//存储各个文档tf的Map

		for (int i = 0; i < D; i++) {
			HashMap<String, Integer> temp = tfInIdf.get(key.get(i));
			for (String word : temp.keySet()) {
				Dt = 1;
				if (!(located.contains(word))) {
					for (int k = 0; k < D; k++) {
						if (k != i) {
							HashMap<String, Integer> temp2 = tfInIdf.get(key.get(k));
							if (temp2.keySet().contains(word)) {
								located.add(word);
								Dt = Dt + 1;
								continue;
							}
						}
					}
					idf.put(word, Log.log((1 + D) / Dt, 10));
				}
			}
		}
		return idf;
	}
}
