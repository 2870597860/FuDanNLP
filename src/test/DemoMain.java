package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;


public class DemoMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> keywords=null;
		int keyWordsNumber=50;
		String News=null;
		String path="E:\\SES和企业信息\\股票期刊论文\\词频统计和分析\\report\\test100\\绿地控股集团股份有限公司.txt";
		//String path="C:\\Users\\dtf\\Desktop\\股评\\2.txt";
		News=readget(News,path);
		GetKeyWords gw=new GetKeyWords();
		try {
			 keywords = gw.GetKeyword(News, keyWordsNumber);
			 /*for (String string : keywords) {
					System.out.println(string);
				}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String readget(String News,String path){
		File file=new File(path);
	
		
			String s ="";
			InputStreamReader isr;
			try {
				isr = new InputStreamReader(new FileInputStream(file),"utf-8");
				BufferedReader br=new BufferedReader(isr);
				while ((s=br.readLine())!=null) {
					News=News+s;
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
	return News;
		
	}

}
