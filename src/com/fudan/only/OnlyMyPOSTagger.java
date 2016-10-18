package com.fudan.only;
import java.util.List;

import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.cn.tag.POSTagger;
import edu.fudan.util.exception.LoadModelException;

/**
 * 词性标注器
 * 先分词，再做词性标注
 * @author xpqiu
 * @version 1.0
 * @since FudanNLP 1.5
 */
public class OnlyMyPOSTagger {
	POSTagger tag;
	public void getPOSTagger(CWSTagger cwst,List<String> baseWords){
		
		try {
				tag = new POSTagger(cwst,"models/pos.m");
				tag.SetTagType("en");//设置标注类型是是中文标注还是英文标注
				//String str1 = "媒体计算 研究所 成立 了 , 高级 数据挖掘 很 难";
				int size=baseWords.size();
				String[] w = baseWords.toArray(new String[size]);
				String[] s1 = tag.tagSeged(w);
				//System.out.println("直接处理分好词的句子");
				for(int i=0;i<s1.length;i++){
				System.out.print(w[i]+"/"+s1[i]+" ");
				}
				System.out.println("\n");
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
