package com.juxdun.analysisTM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/manage")
public class ManageController {

	@RequestMapping("/update")
	public String index(){
		System.out.println("Update");
		return "manage/update";
	}
}
