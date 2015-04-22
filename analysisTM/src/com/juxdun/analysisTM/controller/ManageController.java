package com.juxdun.analysisTM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "success";
	}
}
