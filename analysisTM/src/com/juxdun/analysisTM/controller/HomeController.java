package com.juxdun.analysisTM.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Comment;
import com.juxdun.analysisTM.analysis.entities.Product;
import com.juxdun.analysisTM.analysis.service.AnalysisService;

@Controller
public class HomeController {

	@Autowired
	private AnalysisService service;
	
	@RequestMapping("/home")
	public String home(Map<String, Object> map){
		List<Brand> list = service.listBrands();
		Brand brand = list.get(12);
		List<Product> products = service.listAllProducts();
		Product product = products.get(19);
		List<Comment> comments = service.listCommentByProduct(product);
		
		map.put("brands", list);
		map.put("products", products);
		map.put("comments", comments);
		System.out.println("home");
		return "home";
	}
}
