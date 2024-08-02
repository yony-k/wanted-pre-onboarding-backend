package com.yeon.RecruitmentSite.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeon.RecruitmentSite.dto.ApplicationDTO;
import com.yeon.RecruitmentSite.entity.Application;
import com.yeon.RecruitmentSite.entity.JobOpening;
import com.yeon.RecruitmentSite.entity.User;
import com.yeon.RecruitmentSite.jpaRepo.ApplicationRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApplySvc {

	@Autowired
	private ApplicationRepository applyRepo;
	
	//지원내역 테이블 데이터 확인
	public boolean isApplied(ApplicationDTO applicationDTO) {
		return applyRepo.existsByUser_UserIdAndJobOpening_JobOpeningId
				(applicationDTO.getUserId(), applicationDTO.getJobOpeningId());
	}
	
	//지원내역 테이블에 저장
	@Transactional
	public boolean apply(ApplicationDTO applicationDTO) {
		try {
			Application saved = applyRepo.save(applicationDTO.toEntity());
			return true;
		} catch (Exception e) {
			log.error("ApplySvc apply 메소드 에러: {}", e.getMessage(), e);
            return false;
		}
	}

	//지원내역 테이블에서 삭제
	@Transactional
	public boolean cancleApply(String userId, int jobOpeningId) {
		
		//지원내역 테이블에서 먼저 데이터를 찾은 후 삭제
		try {
			Application findApply = 
					applyRepo.findByUser_UserIdAndJobOpening_JobOpeningId(userId, jobOpeningId);
			
			if(findApply != null) {
				applyRepo.delete(findApply);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("ApplySvc cancleApply 메소드 에러: {}", e.getMessage(), e);
	        return false;
		}
	}
}
