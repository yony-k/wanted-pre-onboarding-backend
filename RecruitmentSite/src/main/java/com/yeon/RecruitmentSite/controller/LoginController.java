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
public class LoginController {
	
	@Autowired
	private LoginSvc loginSvc;
	
	//로그인폼 뷰
	@GetMapping("/form")
	public String loginForm() {
		return "loginForm";
	}
	
	//로그인
	@PostMapping("/sighin")
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDto, 
			Model m,HttpSession session) {
		
		UserDTO user = loginSvc.login(loginDto);
		
		if(user != null) {
			session.setAttribute("user", user);
			return ResponseEntity.ok(Map.of("success", true, "message", "로그인 성공"));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", false, "message", "아이디 혹은 비밀번호를 확인해주세요."));
		}
	}
	
	//로그아웃
	@PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        try {
            session.invalidate();
            return ResponseEntity.ok(Map.of("success", true, "message", "로그아웃 성공"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            		.body(Map.of("success", false, "message", "로그아웃 실패"));
        }
    }

}
