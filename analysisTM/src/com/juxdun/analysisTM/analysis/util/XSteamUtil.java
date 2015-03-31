package com.juxdun.analysisTM.analysis.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.juxdun.analysisTM.analysis.Comment;
import com.juxdun.analysisTM.analysis.Extraction;
import com.thoughtworks.xstream.XStream;

public class XSteamUtil {

	File pathComment = new File(
			"C:/Users/Juxdun/DataScraperWorks/Ju_TM_PHONE_comment");
	public static String XMLsuffix = ".xml";
	
	XStream xstream = new XStream();
	
	/**
	 * 从xml文件取评论列表
	 * @return 评论列表
	 */
	public List<Comment> getCommentsFromXml(){
		List<Comment> comments = new ArrayList<Comment>();
		aliasXSteam(xstream, XSteamAliasMethod.AsComment);
		listComments(pathComment, comments);
		return comments;
	}
/*	public List<Object[]> getBetchArgsFromXml(){
		List<Object[]> betchArgs = new ArrayList<Object[]>();
		aliasXSteam(xstream, XSteamAliasMethod.AsComment);
		listComments(pathComment, betchArgs);
		return betchArgs;
	}*/
	
	/**
	 * 别名映射
	 * 依据不同的xml映射不同的别名
	 * @param xstream 要映射的xstream
	 * @param method  映射的方法
	 */
	private void aliasXSteam(XStream xstream, XSteamAliasMethod method) {
		xstream.alias("extraction", Extraction.class);
		switch (method) {
		case AsBrand:

			break;
		case AsProduct:

			break;
		case AsDetail:

			break;
		case AsComment:
			xstream.aliasField("commets", Extraction.class, "list");

			xstream.alias("item", Comment.class);
			xstream.aliasField("内容", Comment.class, "content");
			xstream.aliasField("日期", Comment.class, "date");
			xstream.aliasField("评论人", Comment.class, "person");
			break;

		default:
			break;
		}
	}
	
	/**
	 * 递归遍历目录取评论
	 * 
	 * @param file
	 *            目录
	 */
	private void listExtraction(File file, List<Extraction> list) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();

			for (int i = 0; i < tempFiles.length; i++) {
				listExtraction(tempFiles[i], list);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，xml转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			list.add(ex);

		}
	}
	
	/**
	 * 递归遍历目录取评论
	 * 
	 * @param file 评论的xml目录
	 * @param list 容器
	 */
	private void listComments(File file, List<Comment> list) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();
			
			for (int i = 0; i < tempFiles.length; i++) {
				listComments(tempFiles[i], list);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，xml转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Comment> tempList = ex.getList();
			// 设置clueid
			for (Comment comment : tempList) {
				comment.setClueid(ex.getClueid());
			}
			list.addAll(tempList);
		}
	}
/*	private void listComments(File file, List<Object[]> betchArgs) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();

			for (int i = 0; i < tempFiles.length; i++) {
				listComments(tempFiles[i], betchArgs);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，xml转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Comment> tempList = ex.getList();
			// 设置clueid
			for (Comment c : tempList) {
				betchArgs.add(new Object[]{ex.getClueid(), c.getContent(), c.getDate(), c.getPerson()});
			}
			
		}
	}*/
	
}
