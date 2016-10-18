package com.fudan.tfidf;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fudan.preprocessing.MyCWSTagger;
import com.fudan.preprocessing.ReadFiles;



public class GetTF {
	private static HashMap<String, HashMap<String, Integer>> allTheNormal_TF = new HashMap<String, HashMap<String, Integer>>();
	private static HashMap<String, HashMap<String, Float>> allTheTfRate=new HashMap<String, HashMap<String, Float>>();
	private static List<String> fileList;//filelist为文件集合

	public static void tfRateNumOfAll(String dir) throws IOException {
		fileList = ReadFiles.readDirs(dir);
		for (String file : fileList) {
			System.out.println(file);
			System.out.println("分词结果如下---");
			String[] CWSTaggerWords=MyCWSTagger.getCWSTagger(file);
			getallTheTRate(file,CWSTaggerWords);
		}

	}  

	//将文章地址以及对应的文章分词进行存储
	public static void getallTheTRate(String file,String[] CWSTaggerWords){
		HashMap<String, Float> dictTF = new HashMap<String, Float>();//存储TF比率
		HashMap<String, Integer> dictIDF = new HashMap<String, Integer>();//存储TF个数
		String[] tagger = CWSTaggerWords;
		dictTF = tf(CWSTaggerWords);
		int i=0;
		System.out.println();
		for (String dict_every : dictTF.keySet()) {//重新获取分词结果
			if (i<=dictTF.size()) {
				tagger[i++]=dict_every;
			}
			System.out.print("-"+dict_every);
		}
		dictIDF =normalTF(tagger);//
		allTheNormal_TF.put(file, dictIDF);//存储词频（词语个数）.
		allTheTfRate.put(file, dictTF);//存储比率
	}
	//计算词频（在这里具体是每个词出现的个数）
	public static HashMap<String, Integer> normalTF(String[] cutWordResult) {
		HashMap<String, Integer> tfNormal = new HashMap<String, Integer>();//没有正规化
		int wordNum = cutWordResult.length;
		int wordtf = 0;
		for (int i = 0; i < wordNum; i++) {
			wordtf = 0;
			if (cutWordResult[i] != " ") {
				for (int j = 0; j < wordNum; j++) {
					if (i != j) {
						if (cutWordResult[i].equals(cutWordResult[j])) {
							cutWordResult[j] = " ";
							wordtf++;
						}
					}
				}
				tfNormal.put(cutWordResult[i], ++wordtf);
				cutWordResult[i] = " ";
			}
		}
		System.out.println();
		System.out.println("在计算idf时的tfNormal："+tfNormal.size());
		System.out.println("------------------------------");
		return tfNormal;
	}
	//计算词频（在这里具体是每个词出现的比率）
	public static HashMap<String, Float> tf(String[] cutWordResult) {
		HashMap<String, Float> tf = new HashMap<String, Float>();//正规化
		int wordNum = cutWordResult.length;
		int wordtf = 0;
		System.out.println();
		for (int i = 0; i < wordNum; i++) {
			wordtf = 0;
			for (int j = 0; j < wordNum; j++) {
				if (cutWordResult[i] != " " && i != j) {
					if (cutWordResult[i].equals(cutWordResult[j])) {
						cutWordResult[j] = " ";
						wordtf++;
					}
				}
			}
			if (cutWordResult[i] != " ") {
				tf.put(cutWordResult[i], (new Float(++wordtf)) / wordNum);
				System.out.print(cutWordResult[i]+":"+wordtf+"、");
				cutWordResult[i] = " ";
			}
		}
		return tf;
	}
	//设置filelist获取入口
	public static List<String> getFileList() {
		return fileList;
	}

	public static HashMap<String, HashMap<String, Float>> getAllTheTfRate() {
		return allTheTfRate;
	}

	public static HashMap<String, HashMap<String, Integer>> getAllTheNormal_TF() {
		return allTheNormal_TF;//存的是概述每个词的个数
	}

}
