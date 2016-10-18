package fenci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fudan.myStopWord.MyStopWord;

import edu.fudan.ml.types.Dictionary;
import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.corpus.StopWords;

public class fenci {
	/** 
     * 主程序 
     * @param args  
     * @throws IOException  
     * @throws   
     */  
    public static void main(String[] args) throws Exception {  
        //创建文件模型，CWTagger from AbstractTagger--为 Linear cl 赋值；  
        //Linear 为线性分类器  
        CWSTagger tag = new CWSTagger("./models/seg.m");  
        System.out.println("不使用词典的分词：");  
        String str = " 媒体计算研究所成立了, 高级数据挖掘(data mining)很难。 ";  
        String s = tag.tag(str);  
        System.out.println(s);  
          
        //设置英文预处理  
        tag.setEnFilter(true);  
        s = tag.tag(str);  
        System.out.println(s);  
        tag.setEnFilter(false);  
        //注：词典里只能有中文字符，英文与数字不支持  
        System.out.println("\n设置临时词典：");  
        ArrayList<String> al = new ArrayList<String>();  
        al.add("数据挖掘");  
//      al.add("媒体计算研究所");  
        Dictionary dict = new Dictionary(false);  
        dict.addSegDict(al);  
        tag.setDictionary(dict);  
        s = tag.tag(str);  
        System.out.println(s);  
          
          
        CWSTagger tag2 = new CWSTagger("./models/seg.m", new Dictionary("./models/dict.txt"));  
        System.out.println("\n使用词典的分词：");  
        String str2 = "中华人民共和国上海交通大学媒体计算研究所成立了，报告期内公司上下团结一心、克难攻坚，主动适应经济新常态，床前明月光，疑是地上霜，持续推进战略转型，全面深化改革创新，在复杂的经济形式中保持了稳中有进的发展态势";  
        String s2 = tag2.tag(str2);
        String[] s22=s2.split("\\s+");
        List<String> temporary=new ArrayList<>();
        for (int i = 0; i < s22.length; i++) {
			if (s22[i].length()>4) {
			temporary.add(s22[i]);
			}
		}
        System.out.println("s22:"+s22.toString());
        StopWords stopWords = new StopWords("./models/stopwords/StopWords.txt");
        // 对分词的结果去除停用词  
        List<String> baseWords02 = stopWords.phraseDel(s22);
        baseWords02.addAll(temporary);
        System.out.println(s2);
        System.out.println(baseWords02);
          
        //使用不严格的词典  
        CWSTagger tag3 = new CWSTagger("./models/seg.m", new Dictionary("./models/dict_ambiguity.txt",true));  
        //尽量满足词典，比如词典中有“成立”“成立了”和“了”, 会使用Viterbi决定更合理的输出  
        System.out.println("\n使用不严格的词典的分词：");  
        String str3 = "媒体计算研究所成立了, 高级数据挖掘很难";  
        String s3 = tag3.tag(str3);  
        System.out.println(s3);  
        str3 = "我送给力学系的同学一个玩具 (送给给力力学力学系都在词典中)";  
        s3 = tag3.tag(str3);  
        System.out.println(s3);  
          
     /*   System.out.println("\n处理文件：");  
        String s4 = tag.tagFile("./example-data/data-tag.txt");  
        System.out.println(s4);*/  
//      tag3.tagFile("./example-data/data-tag.txt","./example-data/data-tag1.txt");  
          
    }  
}
