package com.fudan.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fudan.tfidf.GetTFIDF;
/**
 * 这里仅仅需要文章词频个数和tfidf
 * @author Administrator
 *
 */
public class testMain {
	public static void main(String[] args) throws IOException{
		
		String path="E:\\SES和企业信息\\股票期刊论文\\词频统计和分析\\report\\test100";
		GetTFIDF gettfidf=new GetTFIDF();
		Map<String, LinkedHashMap<String, Float>> tfidf = gettfidf.tfidf(path);
		for (String filename : tfidf.keySet()) {
            System.out.println("fileName " + filename);
            System.out.println(tfidf.get(filename));
        }
	}
	/**
	 *  
	 * @param pathname
	 * @return
	 */
	public static String getText(String pathname){
		File file=new File(pathname);
		BufferedReader br;
		String content="";
		try {
			br=new BufferedReader(new FileReader(file));
			String line=null;
			while ((line=br.readLine())!=null) {
				content=content+line;
			} 
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
		
		
	}
}
