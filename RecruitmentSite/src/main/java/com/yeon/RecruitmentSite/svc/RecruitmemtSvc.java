package com.yeon.RecruitmentSite.svc;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yeon.RecruitmentSite.dto.CompanyDTO;
import com.yeon.RecruitmentSite.dto.JobOpeningDTO;
import com.yeon.RecruitmentSite.entity.Company;
import com.yeon.RecruitmentSite.entity.JobOpening;
import com.yeon.RecruitmentSite.jpaRepo.CompanyRepository;
import com.yeon.RecruitmentSite.jpaRepo.JobOpeningRepository;
import com.yeon.RecruitmentSite.jpaRepo.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecruitmemtSvc {
	
	@Autowired
	private JobOpeningRepository jobRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	//이미지 업로드 경로
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	//이미지 업로드 메소드
	public String uploadFile(MultipartFile file) {
		String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		File saveFile = new File(uploadDir + fileName);

		try {
			file.transferTo(saveFile);
			return "/ckUploads/" + fileName;
		} catch (IllegalStateException | IOException e) {
			log.error("uploadFile 메소드 에러: {}", e.getMessage(), e);
			if(saveFile.exists()) saveFile.delete();
			return null;
		}
	}
	
	//채용공고 DB 등록 메소드
	@Transactional
	public int add(String userId, JobOpeningDTO jobOpeningDto) {
		
		Company company = userRepo.findById(userId).get().getCompany();
		jobOpeningDto.setCompany(CompanyDTO.toDto(company));
		
		try {
			JobOpening saved = jobRepo.save(jobOpeningDto.toEntity());
			return saved.getJobOpeningId();
		} catch (Exception e) {
			log.error("RecruitmemtSvc add 메소드 에러: {}", e.getMessage(), e);
			return 0;
		}
	}
	
	//채용공고 DB 수정 메소드
	@Transactional
	public boolean edit(JobOpeningDTO jobOpeningDto) {

		try {
			JobOpening findEntity = jobRepo.findById(jobOpeningDto.getJobOpeningId())
					.orElse(null);
			
			if(findEntity != null) {
				JobOpeningDTO findDto = JobOpeningDTO.toDto(findEntity);
				findDto.setPosition(jobOpeningDto.getPosition());
				findDto.setCompensation(jobOpeningDto.getCompensation());
				findDto.setSkill(jobOpeningDto.getSkill());
				findDto.setContents(jobOpeningDto.getContents());
				jobRepo.save(findDto.toEntity());
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("RecruitmemtSvc edit 메소드 에러: {}", e.getMessage(), e);
			return false;
		}
	}

	//채용공고 DB 삭제 메소드
	@Transactional
	public boolean delete(int jobOpeningId) {
		
		JobOpening findEntity = jobRepo.findById(jobOpeningId).orElse(null);
		
		try {
			if(findEntity != null) {
				jobRepo.delete(findEntity);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("RecruitmemtSvc delete 메소드 에러: {}", e.getMessage(), e);
			return false;
		}
	}

}
