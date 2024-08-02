package com.yeon.RecruitmentSite.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yeon.RecruitmentSite.dto.JobOpeningDTO;
import com.yeon.RecruitmentSite.dto.UserDTO;
import com.yeon.RecruitmentSite.jpaRepo.JobOpeningRepository;
import com.yeon.RecruitmentSite.svc.RecruitmemtSvc;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/recruitment")
public class RecruitmentController {
	
	@Autowired
	private RecruitmemtSvc recruitmemtSvc;
	
	@Autowired
	private JobOpeningRepository jobRepo;
	
	//채용공고 작성 뷰
	@GetMapping("/add")
	public String addRecruitment() {
		return "addRecruitment";
	}
	
	//ckEditor 이미지 업로드
	@PostMapping("/uploadImage")
	public ResponseEntity<?> uploadImage(@RequestParam("upload") MultipartFile file) {
		String imageURL = recruitmemtSvc.uploadFile(file);
		if(imageURL != null) {
			return ResponseEntity.ok(Map.of("uploaded", true, "url", imageURL));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("uploaded", false, "message", "파일 업로드 실패"));
		}
	}
	
	//ckEditor 이미지 업로드
	@DeleteMapping("/deleteImage")
	public ResponseEntity<?> deleteImage(@RequestBody String url) {
		return null;
	}
	
	//채용공고 등록
	@PostMapping("/add")
	public ResponseEntity<?> postMethodName(HttpSession session,
			@RequestBody JobOpeningDTO jobOpeningDto) {
		UserDTO user = (UserDTO)session.getAttribute("user");
		
		if(user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("success", false, "message", "로그인이 필요합니다."));
		}
		
		int jobOpeningId = recruitmemtSvc.add(user.getUserId(), jobOpeningDto);
		
		if(jobOpeningId != 0) {
			return ResponseEntity.ok
					(Map.of("success", true, 
							"message", "채용공고가 성공적으로 등록되었습니다.",
							"jobOpeningId", jobOpeningId));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("success", false, "message", "채용공고 등록과정에서 오류가 발생했습니다."));
		}
	}
	
	//채용공고 수정 뷰
	@GetMapping("/edit/{jobOpeningId}")
	public String editRecruitment(@PathVariable("jobOpeningId") int jobOpeningId,
			Model m) {
		JobOpeningDTO jobOpeningDto = 
				JobOpeningDTO.toDto(jobRepo.findById(jobOpeningId).get());
		
		m.addAttribute("jobOpening",jobOpeningDto);
		return "editRecruitment";
	}
	
	//채용공고 수정
	@PostMapping("/edit")
	public ResponseEntity<?> edit(HttpSession session,
			@RequestBody JobOpeningDTO jobOpeningDto) {
		UserDTO user = (UserDTO)session.getAttribute("user");
		
		if(user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("success", false, "message", "로그인이 필요합니다."));
		}
		
		boolean ok = recruitmemtSvc.edit(jobOpeningDto);
		
		if(ok) {
			return ResponseEntity.ok
					(Map.of("success", true, "message", "채용공고가 성공적으로 수정되었습니다."));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("success", false, "message", "채용공고 수정과정에서 오류가 발생했습니다."));
		}
	}
	
	//채용공고 삭제
	@DeleteMapping("/delete/{jobOpeningId}")
	public ResponseEntity<?> deleteRecruitment(HttpSession session,
			@PathVariable("jobOpeningId") int jobOpeningId) {
		
		UserDTO user = (UserDTO)session.getAttribute("user");
		
		if(user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("success", false, "message", "로그인이 필요합니다."));
		}
		
		boolean ok = recruitmemtSvc.delete(jobOpeningId);
		
		if(ok) {
			return ResponseEntity.ok
					(Map.of("success", true, "message", "채용공고가 성공적으로 삭제되었습니다."));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("success", false, "message", "채용공고 삭제과정에서 오류가 발생했습니다."));
		}
	}
	
}
