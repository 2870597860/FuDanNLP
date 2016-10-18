package 实现语义依存;


import java.util.HashMap;
import java.util.List;

import com.fudan.myStopWord.MyStopWord;
import com.fudan.preprocessing.ReadFiles;

import edu.fudan.nlp.cn.tag.POSTagger;
import edu.fudan.nlp.parser.dep.DependencyTree;
import edu.fudan.nlp.parser.dep.JointParser;
import edu.fudan.ontology.Dictionary;
import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.cn.tag.NERTagger;

public class function_test {

    private static JointParser parser;

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        parser = new JointParser("models/dep.m");
        String word = "中国进出口银行与中国银行深度加强合作张持良。这是一个可扩展的机器集群华兴资本";
        test_dep(word);
//      test_ner(word);

    }

    /**
     * 测试语义依存
     * 只输入句子，不带词性
     * @throws Exception 
     */
    private static void test_dep(String word) throws Exception {  
    	
        POSTagger tag = new POSTagger("models/seg.m","models/pos.m");
        String[][] s = tag.tag2Array(word);
        try {
            DependencyTree tree = parser.parse2T(s[0],s[1]);
            System.out.println(tree.toString());
            String stree = parser.parse2String(s[0],s[1],true);
            System.out.println(stree);
        } catch (Exception e) {         
            e.printStackTrace();
        }
    }
   
}