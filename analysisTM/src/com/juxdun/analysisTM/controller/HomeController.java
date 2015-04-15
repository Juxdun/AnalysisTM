package com.juxdun.analysisTM.controller;

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
		List<Product> products = service.listAllProducts();

		map.put("brands", list);
		map.put("products", products);
		return "home";
	}

	@ResponseBody
	@RequestMapping("/getproducts")
	public List<Product> getProduct(@RequestParam("brand") Integer productClueid) {
		return service.listProductByProductClueid(productClueid);
	}

	@ResponseBody
	@RequestMapping("/getcomments")
	public List<Comment> getComment(
			@RequestParam("clueid") Integer clueid) {
		return service.listCommentByClueid(clueid);
	}
}
