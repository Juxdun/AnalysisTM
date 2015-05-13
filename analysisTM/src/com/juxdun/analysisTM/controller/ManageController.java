package com.juxdun.analysisTM.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping("getWaComments")
	public List<Comment> getWaComments() {
		return service.getWaComments();
	}
}
