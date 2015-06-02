package com.juxdun.analysisTM.analysis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTxt {
	/**
	 * 读取txt文件接口
	 * @param file 文件
	 * @return String的列表
	 */
	public static List<String> readTxtToList(File file){
		List<String> list = null;
		if (file.isFile() && file.exists()) {
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(new FileInputStream(file), "gbk");
				br = new BufferedReader(isr);
				list = new ArrayList<String>();
				String lineText = null;
				while ((lineText = br.readLine()) != null) {
					lineText = lineText.trim();
					if (lineText != "") {
						list.add(lineText);
					}
				}
				
			} catch (IOException e) {
				System.out.println("流读取错误！");
				e.printStackTrace();
			}finally{
				try {
					if (br != null) {
						br.close();
					}
					if (isr != null) {
						isr.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					System.out.println("流关闭错误！");
				}
			}
		}
		return list;
	}
}
