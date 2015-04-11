package com.juxdun.analysisTM.analysis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juxdun.analysisTM.analysis.dao.CommentDao;
import com.juxdun.analysisTM.analysis.dao.ProductDao;
import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Comment;
import com.juxdun.analysisTM.analysis.entities.Detail;
import com.juxdun.analysisTM.analysis.entities.Product;
import com.juxdun.analysisTM.analysis.service.AnalysisService;
import com.juxdun.analysisTM.analysis.util.XSteamUtil;

@Service
public class AnalysisServiceImpl implements AnalysisService {
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private XSteamUtil xSteamUtil;
	
//private Log log = LogFactory.getLog(AnalysisServiceImpl.class);
//	
//	// 存储路径
//	final File dataHubPath = new File("D:\\TMCommentDataHub");
//	// 准备文件策略同目录savePath
//	final PersistenceStrategy strategy = new FilePersistenceStrategy(dataHubPath);
//	// 创建列表
//	final List dataHubList = new XmlArrayList(strategy);
//
//	
//	// 评论路径
//	final File pathComment = new File(
//			"C:/Users/Juxdun/DataScraperWorks/Ju_TM_PHONE_comment");
//	// xml后缀
//	public static final String XMLsuffix = ".xml";
//
//	// 全部评论的抓包的列表
//	// 每一个Extraction对应一个商品
//	private List<Extraction> commExList;
//	private XStream xstream;

	/**
	 * 去除水军分析
	 * 1.拿到所有的comment
	 * 2.存到数据库
	 * 3.数据库去除水军
	 */
	@Override
	public void analyse() {
		productDao.updateProductTable();
//		commentDao.batchInsertComments(xSteamUtil.getCommentsFromXml());
//		commentDao.deleteWaterArmy();
//		productDao.batchInsertProducts(xSteamUtil.getProducts());
		
//		// 用线程
//		// 1.拿到所有Extraction对象。
//		// 2.合并相同商品的Extraction对象，主要合并其列表
//		// 3.去除水军评论
//		// 4.hash收集有用评论
//		// 5.持久数据
//		dataHubList.clear();
//		
//		
//		//初始化
//		commExList = new ArrayList<Extraction>();
//		xstream = new XStream();
//		
//		// 设置XStream别名映射
//		aliasXSteam(xstream, XSteamAliasMethod.AsComment);
//
//		// 遍历 目录得到所有的对象
//		listExtraction(pathComment);
//		
//		Map<Integer, Extraction> commExtMap = new HashMap<Integer, Extraction>();
//		// 合并相同商品的评论
//		for (Extraction e : commExList) {
//			if (commExtMap.containsKey(e.getClueid().hashCode())) {
//				Extraction temp = commExtMap.get(e.getClueid().hashCode());
//
//				temp.getList().addAll(e.getList());
//			} else {
//				commExtMap.put(e.getClueid().hashCode(), e);
//			}
//		}
//
//System.out.println("===========================");
//
//		// 1.
//		// 遍历一遍
//		Iterator<Extraction> iterator = commExtMap.values().iterator();
//		while (iterator.hasNext()) {
//			Extraction extraction = (Extraction) iterator.next();
//			
//log.info("【前】索引id：" + extraction.getClueid() + "，评论数目："	+ extraction.getList().size());
//
//			// 调用移除水军方法
//			extraction.removeWaterArmyComment();
//
//log.info("【后】索引id：" + extraction.getClueid() + "，评论数目：" + extraction.getList().size() + "\n");			
//
//		}
//log.info("【前】总数目：" + commExList.size() + "，【后】总数目" + commExtMap.size());
//		
//		// 2.
//		// 相同日期 相同人 的整理，Comment类改写了equals和hashCode方法
//		HashMap<Comment, Integer> commentMap = new HashMap<Comment, Integer>();
//		int comCount = 0;
//		// 遍历一遍
//		Iterator<Extraction> it = commExtMap.values().iterator();
//		while (it.hasNext()) {
//			Extraction extraction = (Extraction) it.next();
//			List<Comment> commList = extraction.getList();
//			for (Comment comment : commList) {
//				int count = commentMap.containsKey(comment) ? commentMap.get(comment) : 0;
//				commentMap.put(comment, count + 1);
//				
//			}
//			comCount += commList.size();
//		}
//		List<Integer> result = new ArrayList<Integer>();
//		result.addAll(commentMap.values());
//		Collections.sort(result);
//System.out.println(result);
//log.info("总评论数量：" + comCount);
//		
//		
//		// 整理到List
//		// 每一个List项对应一个商品Extraction
//		commExList.clear();
////		commExList.addAll(commExtMap.values());
//		
//		// 保存
//		dataHubList.addAll(commExtMap.values());
//		
	}

	@Override
	public List<Brand> listBrands() {
		return xSteamUtil.getBrands();
	}

	@Override
	public List<Product> listAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public List<Product> listProductByBrand(Brand brand) {
		return productDao.getProductsByBrand(brand);
	}

	@Override
	public Detail getDetailById(String cludid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> listCommentByProduct(Product product) {
		return commentDao.getCommentsByProduct(product);
	}

}
