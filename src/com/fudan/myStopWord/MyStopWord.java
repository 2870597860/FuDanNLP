package com.fudan.myStopWord;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.corpus.StopWords;

public class MyStopWord {
	private MyStopWord() {
		// TODO Auto-generated constructor stub
	}
	public static List<String> stop(CWSTagger cwst,String[] words,String text){
		/**
		 * 下面使用fudanNLP自带的停用词库模型，可能是噪声问题，将四个字以上的词当做噪声处理掉了（可能是这个原因），，
		 * 为防止将关键词长度大于4的词处理掉，所以在此做一个处理在使用停用词之前先将分词长度大于4的词提取出来，待去除停用词后
		 * 再将这部分内容加进去
		 */
		List<String> temporary=new ArrayList<>();//用来暂时存放4字以上的词语
		for (int i = 0; i < words.length; i++) {
			String word=words[i];
			if (word.length()>4) {//保留大于4个字的词
				if (!HasDigit(word)) {
					if (!word.matches("[a-zA-Z]+")) {//去除五个字符以上的全是字母的词
						temporary.add(words[i]);
					}

				}

			}
		}
		// 加入停用词,对分词的结果去除停用词  
		StopWords stopWords = new StopWords("./models/stopwords/StopWords.txt");
		List<String> baseWords = stopWords.phraseDel(words);
        temporary.addAll(baseWords);//将4个字以上的词语重新加入集合
		return temporary;

	}
	// 判断一个字符串是否含有数字
	public static boolean HasDigit(String content) {
		boolean flag = false;
		Pattern p = Pattern.compile(".*\\d+.*");
		Matcher m = p.matcher(content);
		if (m.matches()) {
			flag = true;
		}
		return flag;
	}
}

