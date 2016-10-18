package com.fudan.only;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.fudan.Sort.HashSortbyvalue;

public class SummDevMain {
public static void main(String[] args) throws IOException{
		
		String path="E:\\SES和企业信息\\股票期刊论文\\词频统计和分析\\report\\test100";
		HashMap<String, Integer> listWords_summ=new HashMap<String, Integer>();//所有文章（每篇文章自身去除重复的词）概要的words
		HashMap<String, Integer> listWords_dev=new HashMap<String, Integer>();//所有文章（每篇文章自身去除重复的词）未来发展的words
		OnlyGetTFNum gettfSD=new OnlyGetTFNum();
		gettfSD.NormalTFOfAll(path);
		listWords_summ=gettfSD.getTxtWordsSort_summ();
		listWords_dev=gettfSD.getTxtWordsSort_dev();
		LinkedHashMap<String, Integer> sortword_summ=new LinkedHashMap<>();
		LinkedHashMap<String, Integer> sortword_dev=new LinkedHashMap<>();
		sortword_summ= (LinkedHashMap<String, Integer>) HashSortbyvalue.sortByValue(listWords_summ);
		sortword_dev= (LinkedHashMap<String, Integer>) HashSortbyvalue.sortByValue(listWords_dev);
		System.err.println();
		System.out.println("print resulr-------");
		System.out.println(sortword_summ.toString());
		System.out.println(sortword_dev.toString());
	}
}
