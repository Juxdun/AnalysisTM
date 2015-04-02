package com.juxdun.analysisTM.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Product;
import com.juxdun.analysisTM.analysis.service.AnalysisService;

@Controller
public class HomeController {

	@Autowired
	private AnalysisService service;
	
	@RequestMapping("/home")
	public String home(Map<String, Object> map){
		List<Brand> list = service.listBrands();
		Brand brand = list.get(5);
		List<Product> products = service.listProductByBrand(brand);
		
		map.put("brands", list);
		map.put("products", products);
		System.out.println("home");
		return "home";
	}
}
