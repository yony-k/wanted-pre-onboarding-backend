package com.yeon.RecruitmentSite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeon.RecruitmentSite.dto.ApplicationDTO;
import com.yeon.RecruitmentSite.dto.UserDTO;
import com.yeon.RecruitmentSite.entity.Application;
import com.yeon.RecruitmentSite.svc.ApplySvc;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/apply")
public class ApplyController {

	@Autowired
	private ApplySvc applySvc;
	
	//채용공고 지원
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<?> apply(HttpSession session, 
			@RequestBody ApplicationDTO applicationDTO) {
		
		//현재 로그인 상태 확인
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		if (user == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("success", false, "message", "로그인이 필요합니다."));
	    }
		
		//기업, 일반회원 구분
		if (user.getUserType().equals("company")) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Map.of("success", false, "message", "기업 회원은 지원하실 수 없습니다."));
	    }
		
		//userId도 한 객체에 저장
		applicationDTO.setUserId(user.getUserId());

		//지원내역 테이블에 데이터가 이미 존재하는지 확인 후 저장
		if (!applySvc.isApplied(applicationDTO)) {
			boolean ok = applySvc.apply(applicationDTO);
			
			if (ok) {
				return ResponseEntity.ok
						(Map.of("success", true, "message", "지원이 성공적으로 완료되었습니다."));
			} else {
				System.out.println("어떤게 돌아가는거임?1");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(Map.of("success", false, "message", "지원 과정에서 오류가 발생했습니다."));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Map.of("success", false, "message", "이미 지원한 공고입니다."));
		}
	}

	//채용공고 지원 취소
	@DeleteMapping("/cancle/{jobOpeningId}")
	public ResponseEntity<?> cancleApply(HttpSession session, 
			@PathVariable("jobOpeningId") int jobOpeningId) {
		
		//현재 로그인 상태 확인
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		if (user == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Map.of("success", false, "message", "로그인이 필요합니다."));
	    }
		
		//DB에서 데이터 삭제
		boolean ok = applySvc.cancleApply(user.getUserId(),jobOpeningId);
		
		if(ok) {
			return ResponseEntity.ok
					(Map.of("success", true, "message", "지원 취소가 성공적으로 완료되었습니다."));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Map.of("success", false, "message", "지원 취소가 실패하였습니다."));
		}
	}

}
