package com.juxdun.analysisTM.analysis.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Comment;
import com.juxdun.analysisTM.analysis.entities.Detail;
import com.juxdun.analysisTM.analysis.entities.Extraction;
import com.juxdun.analysisTM.analysis.entities.Product;
import com.thoughtworks.xstream.XStream;

@Component
public class XSteamUtil {

	public static int BATCH = 10;
	
	File pathBrand = new File(
			"C:/Users/Juxdun/DataScraperWorks/LL_TM_brand");
	File pathProduct = new File(
			"C:/Users/Juxdun/DataScraperWorks/LL_TM_product");
	File pathDetail = new File(
			"C:/Users/Juxdun/DataScraperWorks/LL_TM_detail");
	File pathComment = new File(
			"C:/Users/Juxdun/DataScraperWorks/LL_TM_comment");
//	File pathComment = new File(
//			"C:/Users/Juxdun/DataScraperWorks/test_LL_TM_comment");
	public static String XMLsuffix = ".xml";
	
	/**
	 * 直接把数据读到数据库
	 * @param onDb 每读一个文件执行一次这方法
	 */
	public void brandsToDb(OnInsertDB onDb){
		XStream xstream = new XStream();
		aliasXSteam(xstream, XSteamAliasMethod.AsBrand);
		readBrands2DB(xstream, pathBrand, onDb);
	}

	/**
	 * 递归遍历目录取品牌Brand并存进数据库
	 * @param file XML所在目录
	 * @param list 容器
	 * @param onDb 分段插入数据库回调
	 */
	private void readBrands2DB(XStream xstream, File file, OnInsertDB onDb) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();
			
			for (int i = 0; i < tempFiles.length; i++) {
				readBrands2DB(xstream, tempFiles[i], onDb);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Brand> tempList = (List<Brand>) ex.getList();
			// 调用外部存进数据库方法
			onDb.onInsert(tempList);
		}
	}

	/**
	 * 拿到品牌列表
	 * @return
	 */
	public List<Brand> getBrands(){
		List<Brand> list = new ArrayList<Brand>();
		XStream xstream = new XStream();
		aliasXSteam(xstream, XSteamAliasMethod.AsBrand);
		listBrands(xstream, pathBrand, list);
		return list;
	}
	
	/**
	 * 递归遍历目录取品牌Brand
	 * @param file XML所在目录
	 * @param list 容器
	 */
	private void listBrands(XStream xstream, File file, List<Brand> list) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();
			
			for (int i = 0; i < tempFiles.length; i++) {
				listBrands(xstream, tempFiles[i], list);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Brand> tempList = (List<Brand>) ex.getList();
			
			list.addAll(tempList);
		}
	}

	/**
	 * 直接把商品数据读到数据库
	 * @param onDb 每读一个文件执行一次这方法
	 */
	public void prooductsToDb(OnInsertDB onDb){
		XStream xstream = new XStream();
		aliasXSteam(xstream, XSteamAliasMethod.AsProduct);
		readProducts2DB(xstream, pathProduct, onDb);
	}

	/**
	 * 递归遍历目录取商品Product并存进数据库
	 * @param file XML所在目录
	 * @param list 容器
	 * @param onDb 分段插入数据库回调
	 */
	private void readProducts2DB(XStream xstream, File file, OnInsertDB onDb) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();
			
			for (int i = 0; i < tempFiles.length; i++) {
				readProducts2DB(xstream, tempFiles[i], onDb);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Product> tempList = (List<Product>) ex.getList();
			// 调用外部存进数据库方法
			onDb.onInsert(tempList);
		}
	}
	/**
	 * 拿到产品列表
	 * @return
	 */
	public List<Product> getProducts(){
		List<Product> list = new ArrayList<Product>();
		XStream xstream = new XStream();
		aliasXSteam(xstream, XSteamAliasMethod.AsProduct);
		listProducts(xstream, pathProduct, list);
		return list;
	}
	
	/**
	 * 递归遍历目录取商品
	 * @param file XML所在目录
	 * @param list 容器
	 */
	private void listProducts(XStream xstream, File file, List<Product> list) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();
			
			for (int i = 0; i < tempFiles.length; i++) {
				listProducts(xstream, tempFiles[i], list);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Product> tempList = (List<Product>) ex.getList();
			// 设置ClueId
			/*for (Product p : tempList) {
				p.setClueid(ex.getClueid());
				p.setFullpath(ex.getFullpath());
			}*/
			list.addAll(tempList);
		}
	}
	/**
	 * 递归遍历目录取商品
	 * @param file XML所在目录
	 * @param list 容器
	 */
	/*private void listProducts(File file, List<Product> list) {
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
	}*/
	
	/**
	 * 直接把描述数据读到数据库
	 * @param onDb 每读一个文件执行一次这方法
	 */
	public void detailsToDb(OnInsertDB onDb){
		XStream xstream = new XStream();
		aliasXSteam(xstream, XSteamAliasMethod.AsDetail);
		readProducts2DB(xstream, pathDetail, onDb);
	}

	private void readDetails2DB(XStream xstream, File file, OnInsertDB onDb) {
		if (file.isDirectory()) {
			File[] tempFiles = file.listFiles();
			
			for (int i = 0; i < tempFiles.length; i++) {
				readDetails2DB(xstream, tempFiles[i], onDb);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Detail> tempList = (List<Detail>) ex.getList();
			
			onDb.onInsert(tempList);
		}
	}
	
	/**
	 * 直接把评论数据读到数据库
	 * @param onDb 每读一个文件执行一次这方法
	 */
	public void commentsToDb(OnInsertDB onDb){
		XStream xstream = new XStream();
		aliasXSteam(xstream, XSteamAliasMethod.AsComment);
		readComments2DB(xstream, pathComment, onDb);
	}

	/**
	 * 递归遍历目录取评论Comment并存进数据库
	 * @param file XML所在目录
	 * @param list 容器
	 * @param onDb 分段插入数据库回调
	 */
	private void readComments2DB(XStream xstream, File file, OnInsertDB onDb) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();
			
			for (int i = 0; i < tempFiles.length; i++) {
				readComments2DB(xstream, tempFiles[i], onDb);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Comment> tempList = (List<Comment>) ex.getList();
			for (Comment comment : tempList) {
				comment.setBaseURI(ex.getBaseURI().getBucketBaseURI().getUri());
			}
			// 调用外部存进数据库方法
			onDb.onInsert(tempList);
		}
	}
	
	/**
	 * 从xml文件取评论列表
	 * @return 评论列表
	 */
	public List<Comment> getCommentsFromXml(){
		List<Comment> list = new ArrayList<Comment>();
		XStream xstream = new XStream();
		aliasXSteam(xstream, XSteamAliasMethod.AsComment);
		listComments(xstream, pathComment, list);
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
	private void listComments(XStream xstream, File file, List<Comment> list) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();
			
			for (int i = 0; i < tempFiles.length; i++) {
				listComments(xstream, tempFiles[i], list);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			List<Comment> tempList = (List<Comment>) ex.getList();
			// 设置ClueId
			for (Comment comment : tempList) {
//				comment.setClueid(ex.getClueid());
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
			xstream.aliasField("brandlist", Extraction.class, "list");
			
			xstream.alias("item", Brand.class);
			xstream.aliasField("name", Brand.class, "name");
			xstream.aliasField("page", Brand.class, "page");
			xstream.aliasField("img", Brand.class, "img");
			break;
		case AsProduct:
			xstream.aliasField("products", Extraction.class, "list");
			
			xstream.alias("item", Product.class);
			xstream.aliasField("name", Product.class, "name");
			xstream.aliasField("resume", Product.class, "resume");
			xstream.aliasField("price", Product.class, "price");
			xstream.aliasField("img", Product.class, "img");
			xstream.aliasField("monTradeVol", Product.class, "monTradeVol");
			xstream.aliasField("page", Product.class, "page");
			break;
		case AsDetail:
			xstream.aliasField("details", Extraction.class, "list");
			
			xstream.alias("item", Detail.class);
			xstream.aliasField("name", Detail.class, "name");
			xstream.aliasField("resume", Detail.class, "resume");
			xstream.aliasField("monSaleVol", Detail.class, "monSaleVol");
			xstream.aliasField("cumuComment", Detail.class, "cumuComment");
			break;
		case AsComment:
			xstream.aliasField("comments", Extraction.class, "list");

			xstream.alias("item", Comment.class);
			xstream.aliasField("content", Comment.class, "content");
			xstream.aliasField("append", Comment.class, "append");
			xstream.aliasField("date", Comment.class, "date");
			xstream.aliasField("person", Comment.class, "person");
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
		XStream xstream = new XStream();
		aliasXSteam(xstream, XSteamAliasMethod.AsComment);
		listExtraction(xstream, pathComment, list);
		return list;
	}
	
	/**
	 * 递归遍历目录取评论
	 * @param file 目录
	 * @param list 容器
	 */
	private void listExtraction(XStream xstream, File file, List<Extraction> list) {
		if (file.isDirectory()) {
			// 如果是目录，递归遍历目录
			File[] tempFiles = file.listFiles();

			for (int i = 0; i < tempFiles.length; i++) {
				listExtraction(xstream, tempFiles[i], list);
			}
		} else if (file.isFile() && file.getName().endsWith(XMLsuffix)) {
			// 如果是文件，XML转换Object
			Extraction ex = (Extraction) xstream.fromXML(file);
			list.add(ex);
		}
	}

	// 一边拿数据一边写入数据库接口
	public interface OnInsertDB{
		void onInsert(List<?> list);
	}
}
