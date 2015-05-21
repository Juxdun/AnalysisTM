package com.juxdun.analysisTM.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juxdun.analysisTM.analysis.entities.Comment;
import com.juxdun.analysisTM.analysis.service.AnalysisService;

@Controller
public class ManageController {
	
	@Autowired
	private AnalysisService service;

	@RequestMapping("/update")
	public String index(){
		System.out.println("Update");
		return "manage/update";
	}
	
	@RequestMapping("analysis")
	public String analysis(){
		service.analyse();
		return "manage/success";
	}
	
	@ResponseBody
	@RequestMapping("getWaCount")
	public List<Integer> getWaCount() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(service.getWaCount());
		list.add(service.getAllCount());
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "changeProInfo", method=RequestMethod.POST)
	public Boolean changeProInfo(@RequestParam("id") Integer id, @RequestParam("name") String name, 
			@RequestParam("resume") String resume, @RequestParam("price") String price, @RequestParam("img") String img,
			@RequestParam("star") Integer star) {
		/*String n=null, r = null;
		try {
			n = new String(name.getBytes("iso8859-1"),"UTF-8");
			r = new String(resume.getBytes("iso8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}*/
		return service.updateProductInfo(id, name, resume, price, img, star);
	}
	
	@ResponseBody
	@RequestMapping(value = "insertBrands", method=RequestMethod.POST)
	public Boolean insertBrands() {
		return service.insertBrands();
	}
	
	@ResponseBody
	@RequestMapping(value = "insertProducts", method=RequestMethod.POST)
	public Boolean insertProducts() {
		return service.insertProducts();
	}
	
	@ResponseBody
	@RequestMapping(value = "insertComments", method=RequestMethod.POST)
	public Boolean insertComments() {
		return service.insertComments();
	}
	
	@ResponseBody
	@RequestMapping(value = "linkB2P", method=RequestMethod.POST)
	public Boolean linkB2P() {
		return service.linkB2P();
	}
	
	@ResponseBody
	@RequestMapping(value = "linkP2C", method=RequestMethod.POST)
	public Boolean linkP2C() {
		return service.linkP2C();
	}
	
	@ResponseBody
	@RequestMapping(value = "insertAndLink", method=RequestMethod.POST)
	public Boolean insertAndLink() {
		return service.insertAndLink();
	}
	
	@ResponseBody
	@RequestMapping(value = "signWater", method=RequestMethod.POST)
	public Boolean signWater() {
		return service.signWater();
	}
	
	@ResponseBody
	@RequestMapping(value = "analysePositive", method=RequestMethod.POST)
	public Boolean analysePositive() {
		return service.analysePositive();
	}
	
	@ResponseBody
	@RequestMapping(value = "analyseNegative", method=RequestMethod.POST)
	public Boolean analyseNegative() {
		return service.analyseNegative();
	}
	
	@ResponseBody
	@RequestMapping(value = "countComment", method=RequestMethod.POST)
	public Boolean countComment() {
		return service.countComment();
	}
	
	@ResponseBody
	@RequestMapping(value = "countWater", method=RequestMethod.POST)
	public Boolean countWater() {
		return service.countWater();
	}
	
	@ResponseBody
	@RequestMapping(value = "countGood", method=RequestMethod.POST)
	public Boolean countGood() {
		return service.countGood();
	}
	
	@ResponseBody
	@RequestMapping(value = "countBad", method=RequestMethod.POST)
	public Boolean countBad() {
		return service.countBad();
	}
	
	@ResponseBody
	@RequestMapping(value = "analyseAndCount", method=RequestMethod.POST)
	public Boolean analyseAndCount() {
		return service.analyseAndCount();
	}
	
	
	@ResponseBody
	@RequestMapping("getWaComments")
	public List<Comment> getWaComments() {
		return service.getWaComments();
	}
}
