package com.juxdun.analysisTM.analysis.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Comment;
import com.juxdun.analysisTM.analysis.entities.Detail;
import com.juxdun.analysisTM.analysis.entities.Extraction;
import com.juxdun.analysisTM.analysis.entities.Product;
import com.thoughtworks.xstream.XStream;

public class XSteamUtil {

	File pathBrand = new File(
			"C:/Users/Juxdun/DataScraperWorks/Ju_TM_PHONE_category");
	File pathProduct = new File(
			"C:/Users/Juxdun/DataScraperWorks/Ju_TM_PHONE_list");
	File pathDetail = new File(
			"C:/Users/Juxdun/DataScraperWorks/Ju_TM_PHONE_detail");
	File pathComment = new File(
			"C:/Users/Juxdun/DataScraperWorks/Ju_TM_PHONE_comment");
	public static String XMLsuffix = ".xml";
	
	XStream xstream = new XStream();
	
	/**
	 * 拿到品牌列表
	 * @return
	 */
	public List<Brand> getBrands(){
		List<Brand> list = new ArrayList<Brand>();
		aliasXSteam(xstream, XSteamAliasMethod.AsBrand);
		listBrands(pathBrand, list);
		return list;
	}
	
	/**
	 * 递归遍历目录取品牌Brand
	 * @param file XML所在目录
	 * @param list 容器
	 */
	private void listBrands(File file, List<Brand> list) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();
			
			for (int i = 0; i < tempFiles.length; i++) {
				listBrands(tempFiles[i], list);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Brand> tempList = ex.getList();
			
			list.addAll(tempList);
		}
	}

	/**
	 * 拿到产品列表
	 * @return
	 */
	public List<Product> getProducts(){
		List<Product> list = new ArrayList<Product>();
		aliasXSteam(xstream, XSteamAliasMethod.AsProduct);
		listProducts(pathProduct, list);
		return list;
	}
	
	/**
	 * 递归遍历目录取商品
	 * @param file XML所在目录
	 * @param list 容器
	 */
	private void listProducts(File file, List<Product> list) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();
			
			for (int i = 0; i < tempFiles.length; i++) {
				listProducts(tempFiles[i], list);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Product> tempList = ex.getList();
			// 设置ClueId
			for (Product p : tempList) {
				p.setClueid(ex.getClueid());
				p.setFullpath(ex.getFullpath());
			}
			list.addAll(tempList);
		}
	}

	/**
	 * 从xml文件取评论列表
	 * @return 评论列表
	 */
	public List<Comment> getCommentsFromXml(){
		List<Comment> list = new ArrayList<Comment>();
		aliasXSteam(xstream, XSteamAliasMethod.AsComment);
		listComments(pathComment, list);
		return list;
	}
/*	public List<Object[]> getBetchArgsFromXml(){
		List<Object[]> betchArgs = new ArrayList<Object[]>();
		aliasXSteam(xstream, XSteamAliasMethod.AsComment);
		listComments(pathComment, betchArgs);
		return betchArgs;
	}*/
	
	/**
	 * 递归遍历目录取评论
	 * @param file XML所在目录
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
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Comment> tempList = ex.getList();
			// 设置ClueId
			for (Comment comment : tempList) {
				comment.setClueid(ex.getClueid());
				comment.setBaseURI(ex.getBaseURI().getBucketBaseURI().getUri());
			}
			list.addAll(tempList);
		}
	}

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
			//				xml标签名		映射类		映射属性
			xstream.aliasField("brands", Extraction.class, "list");
			
			xstream.alias("item", Brand.class);
			xstream.aliasField("name", Brand.class, "name");
			xstream.aliasField("page", Brand.class, "page");
			xstream.aliasField("img", Brand.class, "img");
			break;
		case AsProduct:
			xstream.aliasField("products", Extraction.class, "list");
			
			xstream.alias("item", Product.class);
			xstream.aliasField("商品名", Product.class, "name");
			xstream.aliasField("价格", Product.class, "price");
			xstream.aliasField("page", Product.class, "page");
			xstream.aliasField("img", Product.class, "img");
			xstream.aliasField("商家名", Product.class, "shopName");
			xstream.aliasField("商家page", Product.class, "shopUrl");
			break;
		case AsDetail:
			xstream.aliasField("detail", Extraction.class, "list");
			
			xstream.alias("item", Detail.class);
			xstream.aliasField("商品名", Detail.class, "name");
			xstream.aliasField("商品描述", Detail.class, "detail");
			xstream.aliasField("价格", Detail.class, "price");
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
	 * 从xml文件取评论列表
	 * @return 评论列表
	 */
	public List<Extraction> getCommentExtractions(){
		List<Extraction> list = new ArrayList<Extraction>();
		aliasXSteam(xstream, XSteamAliasMethod.AsComment);
		listExtraction(pathComment, list);
		return list;
	}
	
	/**
	 * 递归遍历目录取评论
	 * @param file 目录
	 * @param list 容器
	 */
	private void listExtraction(File file, List<Extraction> list) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();

			for (int i = 0; i < tempFiles.length; i++) {
				listExtraction(tempFiles[i], list);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			list.add(ex);
		}
	}
	
}
