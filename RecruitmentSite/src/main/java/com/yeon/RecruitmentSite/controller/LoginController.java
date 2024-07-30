package com.yeon.RecruitmentSite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeon.RecruitmentSite.dto.CompanyDTO;
import com.yeon.RecruitmentSite.dto.JobOpeningDTO;
import com.yeon.RecruitmentSite.dto.LoginDTO;
import com.yeon.RecruitmentSite.dto.UserDTO;
import com.yeon.RecruitmentSite.entity.User;
import com.yeon.RecruitmentSite.jpaRepo.UserRepository;
import com.yeon.RecruitmentSite.svc.LoginSvc;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {
	
	@Autowired
	private LoginSvc loginSvc;
	
	@GetMapping("/form")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/sighin")
	@ResponseBody
	public ResponseEntity<?> login(@ModelAttribute LoginDTO loginDto, Model m) {
		
		UserDTO user = loginSvc.login(loginDto);
		
		if(user != null) {
			m.addAttribute("user",user);
			return ResponseEntity.ok(Map.of("success", true));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", false, "error", "아이디 혹은 비밀번호를 확인해주세요."));
		}
	}
	
	@PostMapping("/logout")
	@ResponseBody
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok().build();
	}

}
