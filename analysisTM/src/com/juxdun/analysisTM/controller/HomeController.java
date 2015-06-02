package com.juxdun.analysisTM.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Comment;
import com.juxdun.analysisTM.analysis.entities.Product;
import com.juxdun.analysisTM.analysis.service.AnalysisService;

@Controller
public class HomeController {

	@Autowired
	private AnalysisService service;

	@RequestMapping("/home")
	public String home(Map<String, Object> map) {
		List<Brand> list = service.listBrands();
//		List<Product> products = service.listAllProducts();

		map.put("brands", list);
//		map.put("products", products);
		return "home";
	}

	@ResponseBody
	@RequestMapping("/getAllProducts")
	public List<Product> getAllProduct() {
		return service.listAllProducts();
	}
	
	@ResponseBody
	@RequestMapping("/getproducts")
	public List<Product> getProduct(@RequestParam("brand") Integer brandId) {
		return service.listProductByBrandId(brandId);
	}
	
	@ResponseBody
	@RequestMapping("/getpro")
	public Product getProductById(@RequestParam("productId") Integer productId) {
		return service.getProductById(productId);
	}

	@ResponseBody
	@RequestMapping("getStarComments")
	public List<Comment> getStarComments(
			@RequestParam("id") Integer id) {
		return service.listCommentByProductId(id);
	}
	
	@ResponseBody
	@RequestMapping("/getKeywordComments")
	public List<Comment> getKeywordComments(
			@RequestParam("kw") String keyword, @RequestParam("id") Integer clueid) {
		String s = null;
		try {
			s = new String(keyword.getBytes("iso8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		return service.getKeywordComments(clueid, s);
	}
	
	@ResponseBody
	@RequestMapping("/search")
	public List<Product> search(@RequestParam("wd") String wd) {
		String s = null;
		try {
			s = new String(wd.getBytes("iso8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		return service.searchProduct(s);
	}
}
