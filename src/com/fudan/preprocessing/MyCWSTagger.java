package com.fudan.preprocessing;

import java.util.Arrays;
import java.util.List;
import com.fudan.myStopWord.MyStopWord;
import edu.fudan.ml.types.Dictionary;
import edu.fudan.nlp.cn.tag.CWSTagger;

public class MyCWSTagger {	
	 public static String[]  getCWSTagger(String file){
			String[] allWords = null;//存储概述分词结果
			try {
				String text =ReadFiles.readFiles(file);//获取每篇txt文本
				// seg.m为模型文件名  ,并加入自定义词典
	            CWSTagger cwst = new CWSTagger("./models/seg.m",new Dictionary("./models/dict.txt"));  
				/**
				 * 下面使用fudanNLP自带的停用词库模型，可能是噪声问题，将四个字以上的词当做噪声处理掉了（可能是这个原因），，
				 * 为防止将关键词长度大于4的词处理掉，所以在此做一个处理在使用停用词之前先将分词长度大于4的词提取出来，待去除停用词后
				 * 再将这部分内容加进去
				 */
	            String[] words = cwst.tag(text).split("\\s+");//正则表达式中\s匹配任何空白字符，包括空格、制表符、换页符等等 
	            // 加入停用词,对分词的结果去除停用词
	            allWords=getMyStopWord(cwst, words, text);
	            
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return allWords;
		}
	    public static String[] getMyStopWord(CWSTagger cwst,String[] words,String text){
			 String[] CWSTaggerWords=null;
			 //自己设定规则保留四个字以上的词语，并去除全是字母的词
			 List<String> allTaggerWords=MyStopWord.stop(cwst,words,text);
			 int size=allTaggerWords.size();
			 CWSTaggerWords = allTaggerWords.toArray(new String[size]);
	        //myPOSTagger.getPOSTagger(cwst,baseWords);
			 System.out.println();
	        System.out.println("分词总数："+allTaggerWords.size());
	        for(int i=0; i<allTaggerWords.size(); i++) {  
	        	
	            System.out.print(allTaggerWords.get(i) + " ");                 
	        }
	        return CWSTaggerWords;
		}
}
