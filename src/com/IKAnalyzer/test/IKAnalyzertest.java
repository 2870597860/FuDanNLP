package com.IKAnalyzer.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.dic.Dictionary;
/**
 * 分词效果不是太理想
 * @author Administrator
 *
 */
public class IKAnalyzertest {
	public static void main(String[] args){
        String str = "马云和马化腾是两个牛人";

        IKAnalysis(str);
    }

    public static String IKAnalysis(String str) {
        StringBuffer sb = new StringBuffer();
        try {
            byte[] bt = str.getBytes();
            InputStream ip = new ByteArrayInputStream(bt);
            Reader read = new InputStreamReader(ip);
            IKSegmenter iks = new IKSegmenter(read, false);
            Lexeme t;
            while ((t = iks.next()) != null) {
                sb.append(t.getLexemeText() + " , ");
            }
            sb.delete(sb.length() - 1, sb.length());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}