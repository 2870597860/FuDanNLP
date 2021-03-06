package com.fudan.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ReadFiles {
	//定义读取文件集合
	static List<String> fileList = new ArrayList<String>();
	//读取目录中的所有文件
	public static List<String> readDirs(String filepath) throws FileNotFoundException, IOException {
        try {
            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("输入的参数应该为[文件夹名]");
                System.out.println("filepath: " + file.getAbsolutePath());
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        //System.out.println("filepath: " + readfile.getAbsolutePath());
                        fileList.add(readfile.getAbsolutePath());
                    } else if (readfile.isDirectory()) {
                        readDirs(filepath + "\\" + filelist[i]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return fileList;
    } 
	//读取每个文件中的文本内容
	 public static String readFiles(String file) throws FileNotFoundException, IOException {
	        StringBuffer sb = new StringBuffer();
	        InputStreamReader is = new InputStreamReader(new FileInputStream(file), "utf-8");
	        BufferedReader br = new BufferedReader(is);
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line).append("\r\n");
	            line = br.readLine();
	        }
	        br.close(); 
	        return sb.toString();
	    }

}
