package com.fudan.only;

import java.util.ArrayList;
import java.util.List;
/**
 * 将管理层讨论和分析分为两部分一部分为概述，一部分为展望进行分别存储
 * 以未来发展为分界点
 * @author Administrator
 *
 */
public class Compare {
	public static List<String> summary=new ArrayList<>();//存储
	public static List<String> development=new ArrayList<>();
	public static void getSummDlp(String[] words){
		summary.clear();
		development.clear();
		String flag="未来发展";
		int index=0;
		
		for (int i = 0; i < words.length; i++) {
			String str=words[i];
			if (str.trim().equals(flag)) {
				index=i;
				break;
			}
		}
		for (int i = 0; i < words.length; i++) {
			if (i<index) {
				summary.add(words[i]);
			}else {
				development.add(words[i]);
			}
		}
	}
}
