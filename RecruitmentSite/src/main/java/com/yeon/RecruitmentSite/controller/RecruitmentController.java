package com.yeon.RecruitmentSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeon.RecruitmentSite.svc.RecruitmentSvc;

@Controller
@RequestMapping("/home")
public class RecruitmentController {
	
	@Autowired
	private RecruitmentSvc recruitmentSvc;
	
	@GetMapping("")
	public String recruitmentList(Model m) {
		m.addAttribute("jobOpeningList",recruitmentSvc.jobOpeningList());
		return "home";
	}

}
