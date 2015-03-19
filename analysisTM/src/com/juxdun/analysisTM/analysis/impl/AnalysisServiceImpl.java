package com.juxdun.analysisTM.analysis.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.juxdun.analysisTM.analysis.AnalysisService;
import com.juxdun.analysisTM.analysis.Brand;
import com.juxdun.analysisTM.analysis.Comment;
import com.juxdun.analysisTM.analysis.Detail;
import com.juxdun.analysisTM.analysis.Extraction;
import com.juxdun.analysisTM.analysis.Product;
import com.juxdun.analysisTM.analysis.util.XSteamAliasMethod;
import com.thoughtworks.xstream.XStream;

public class AnalysisServiceImpl implements AnalysisService {

	// 评论路径
	File pathComment = new File(
			"C:/Users/Juxdun/DataScraperWorks/Ju_TM_PHONE_comment");
	// xml后缀
	public static String XMLsuffix = ".xml";

	// 全部评论的抓包的列表
	// 每一个Extraction对应一个商品
	private List<Extraction> commExList;
	
	private XStream xstream;

	@Override
	public void analyse() {
		// 用线程
		// 1.拿到所有Extraction对象。
		// 2.合并相同商品的Extraction对象，主要合并其列表
		// 3.去除水军评论
		// 4.hash收集有用评论
		// 5.持久数据
		
		//初始化
		commExList = new ArrayList<Extraction>();
		xstream = new XStream();
		
		// 设置XStream别名映射
		aliasXSteam(xstream, XSteamAliasMethod.AsComment);

		// 遍历 目录得到所有的对象
		listExtraction(pathComment);
		// 合并相同商品的评论
		Map<Integer, Extraction> co = new HashMap<Integer, Extraction>();
		for (Extraction e : commExList) {
			if (co.containsKey(e.getClueid().hashCode())) {
				Extraction temp = co.get(e.getClueid().hashCode());

				temp.getList().addAll(e.getList());
			} else {
				co.put(e.getClueid().hashCode(), e);
			}
		}

		System.out.println("===========================");

		// 遍历
		Iterator<Extraction> iterator = co.values().iterator();
		while (iterator.hasNext()) {
			Extraction extraction = (Extraction) iterator.next();
			
			System.out.println("前" + extraction.getClueid() + "--Size:"
					+ extraction.getList().size());

			// 调用移除水军方法
			extraction.removeWaterArmyComment();
			
			System.out.println("后" + extraction.getClueid() + "--Size:"
					+ extraction.getList().size() + "\n");
		}
		System.out.println(commExList.size() + "  " + co.size());
	}

	// TODO
	/**
	 * 别名映射
	 * 依据不同的xml映射不同的别名
	 * @param xstream 要映射的xstream
	 * @param method  映射的方法
	 */
	private void aliasXSteam(XStream xstream, XSteamAliasMethod method) {
		switch (method) {
		case AsBrand:

			break;
		case AsProduct:

			break;
		case AsDetail:

			break;
		case AsComment:
			xstream.alias("extraction", Extraction.class);
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
	private void listExtraction(File file) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();

			for (int i = 0; i < tempFiles.length; i++) {
				listExtraction(tempFiles[i]);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，xml转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			commExList.add(ex);

			System.out
					.println(ex.getClueid() + "--Size:" + ex.getList().size());

		}
	}

	@Override
	public List<Brand> listBrands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> listAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> listProductByBrand(String cludid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Detail getDetailById(String cludid) {
		// TODO Auto-generated method stub
		return null;
	}

}
