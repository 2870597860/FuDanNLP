package com.fudan.only;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fudan.Sort.HashSortbyvalue;
import com.fudan.preprocessing.MyCWSTagger;
import com.fudan.preprocessing.ReadFiles;
public class OnlyGetTFNum {
	//存的是概述每个词的个数
	public  HashMap<String, HashMap<String, Integer>> allTheNormal_TF_summ = new HashMap<String, HashMap<String, Integer>>();
	//存的是未来发展每个词的个数
	public  HashMap<String, HashMap<String, Integer>> allTheNormal_TF_dev = new HashMap<String, HashMap<String, Integer>>();//存的是未来发展每个词的个数
	private  HashMap<String, Integer> txtWordsSort_summ=new HashMap<>();
	private  HashMap<String, Integer> txtWordsSort_dev=new HashMap<>();
	private  List<String> fileList;//filelist为文件集合
	//设置filelist获取入口
	public  List<String> getFileList() {
		return fileList;
	}

	public HashMap<String, Integer> getTxtWordsSort_summ() {
		return txtWordsSort_summ;
	}
	public HashMap<String, Integer> getTxtWordsSort_dev() {
		return txtWordsSort_dev;
	}
	public  void  NormalTFOfAll(String dir) throws IOException {
		List<String> listWords_summ=new ArrayList<>();//所有文章（每篇文章自身去除重复的词）概要的words
		List<String> listWords_dev=new ArrayList<>();//所有文章（每篇文章自身去除重复的词）未来发展的words
		fileList = ReadFiles.readDirs(dir);
		for (String file : fileList) {
			System.out.println();
			System.out.println(file);
			System.out.println("分词结果如下---");
			OnlyMyCWSTagger.getCWSTagger(file);//对每一篇文章进行分词即分为两部分概述和未来发展
			
			String[] CWSTaggerWords_summ=OnlyMyCWSTagger.temporary_summ;
			String[] CWSTaggerWords_dev=OnlyMyCWSTagger.temporary_dev;
			
			allTheNormal_TF_summ.putAll(getNumTf(file,CWSTaggerWords_summ));//暂留未用
			LinkedHashMap<String, Integer> sortword=new LinkedHashMap<>();
			sortword= (LinkedHashMap<String, Integer>) HashSortbyvalue.sortByValue(allTheNormal_TF_summ.get(file));
			System.out.println("\n"+sortword.toString());
			
			allTheNormal_TF_dev.putAll(getNumTf(file,CWSTaggerWords_dev));//暂留未用
			sortword.clear();
			sortword= (LinkedHashMap<String, Integer>) HashSortbyvalue.sortByValue(allTheNormal_TF_dev.get(file));
			System.out.println(sortword.toString());
			
			//将每一篇两部分的词进行分别存储(前提是对每一篇的重复词进行处理)
			listWords_summ.addAll(getDelDupWords(CWSTaggerWords_summ));			
			listWords_dev.addAll(getDelDupWords(CWSTaggerWords_dev));
		}	
		//获取词频(即词语个数)存入集合
		txtWordsSort_summ.putAll(getAlltxtWords(listWords_summ));//对所有词进行统计词频（概述中）
		System.out.println("\n"+"summ"+txtWordsSort_summ.size());
		txtWordsSort_dev.putAll(getAlltxtWords(listWords_dev));//对所有词进行统计词频（未来发展中）
		System.out.println("\n"+"dev"+txtWordsSort_dev.size());
	}  
	//将文章地址以及对应的文章分词进行存储
	private  HashMap<String, HashMap<String, Integer>> getNumTf(String file,String[] CWSTaggerWords){
		HashMap<String, Integer> dictTFNum = new HashMap<String, Integer>();//存储TF个数（每个词的）
		HashMap<String, HashMap<String, Integer>> allTheNormal_TF = new HashMap<String, HashMap<String, Integer>>();
		String[] tagger = CWSTaggerWords;
		int i=0;
		//write(file,dictTFNum);
		dictTFNum = normalTF(CWSTaggerWords);
		for (String dict_every : dictTFNum.keySet()) {//重新获取分词结果
			if (i<=dictTFNum.size()) {
				tagger[i++]=dict_every;
			}
		}
		allTheNormal_TF.put(file, dictTFNum);//在本次中计算idf，就不需要分为概述和未来展望两部分
		return allTheNormal_TF;
	}
	public  HashMap<String, Integer> normalTF(String[] cutWordResult) {
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
		return tfNormal;
	}
	//对每篇文章去除重复的词，然后再将所有文章的词存储到List<String>中
	public List<String> getDelDupWords(String[] CWSTaggerWords){
		Set<String> temporaryWords=new HashSet<>(Arrays.asList(CWSTaggerWords));
		List<String> words=new ArrayList<>();
		words.addAll(temporaryWords);
		System.out.println("\n去除重复词语后："+words.size());
		return words;
	}
	//将所有的文本的词计算词频（即每个词的个数），然后排序获得降序
	public HashMap<String, Integer> getAlltxtWords(List<String> sortWords){
		HashMap<String, Integer> dictNum = new HashMap<String, Integer>();
		String[] preSort=sortWords.toArray(new String[sortWords.size()]);
		dictNum=normalTF(preSort);
		return dictNum;
	}
	private void write(String filename,HashMap<String, Integer> dictTFNum){
		String str=filename+"\n"+dictTFNum.toString();
		String pathname="./wordsResult.txt";
		File file=new File(pathname);
		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter(file.getName(),true); 
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(str);
			bufferWritter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}
}
