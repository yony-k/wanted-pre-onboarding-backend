package com.yeon.RecruitmentSite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeon.RecruitmentSite.dto.JobOpeningDTO;
import com.yeon.RecruitmentSite.dto.UserDTO;
import com.yeon.RecruitmentSite.svc.HomeSvc;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private HomeSvc homeSvc;
	
	//채용공고 목록
	@GetMapping("home")
	public String recruitmentList(Model m) {
		m.addAttribute("jobOpeningList",homeSvc.jobOpeningList());
		return "home";
	}
	
	//채용공고 상세
	@GetMapping("recruitmentDetail/{jobOpeningId}")
	public String recruitmentDetail(@PathVariable("jobOpeningId") int jobOpeningId, Model m) {
		
		JobOpeningDTO jobOpening = homeSvc.jobOpeningDetail(jobOpeningId);
		m.addAttribute("jobOpening",jobOpening);
		m.addAttribute("listByCompany",
				homeSvc.jobOpeningListByCompany(jobOpening.getCompany().getCompanyId()));
		return "recruitmentDetail";
	}

	//채용공고 검색
	@GetMapping("serch")
	public String getMethodName(@RequestParam("category") String category, 
			@RequestParam("keyword") String keyword,
			Model m) {
		
		List <JobOpeningDTO> serchList = homeSvc.serch(category, keyword);
		m.addAttribute("serchResult", serchList);
		return "serchRecruitment";
	}
	
	//마이페이지
	@GetMapping("mypage")
	public String getMethodName(HttpSession session, Model m) {
		UserDTO user = (UserDTO)session.getAttribute("user");
		
		if(user.getUserType().equals("user")) {
			m.addAttribute("applicationList", homeSvc.applicationList(user.getUserId()));
			return "userMypage";
		} else {
			m.addAttribute("listByCompany", homeSvc.jobOpeningListByCompany(user.getCompanyId()));
			return "companyMypage";
		}
	}
	
	
}
