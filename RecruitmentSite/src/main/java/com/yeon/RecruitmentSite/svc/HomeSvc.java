package com.yeon.RecruitmentSite.svc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeon.RecruitmentSite.dto.ApplicationDTO;
import com.yeon.RecruitmentSite.dto.JobOpeningDTO;
import com.yeon.RecruitmentSite.entity.Application;
import com.yeon.RecruitmentSite.entity.JobOpening;
import com.yeon.RecruitmentSite.jpaRepo.ApplicationRepository;
import com.yeon.RecruitmentSite.jpaRepo.JobOpeningRepository;

@Service
public class HomeSvc {
	
	@Autowired
	private JobOpeningRepository jobRepo;
	
	@Autowired
	private ApplicationRepository applyRepo;
	
	//채용공고 목록
	public List<JobOpeningDTO> jobOpeningList() {
		
		List<JobOpeningDTO> jobOpeningList = jobRepo.findAll()
				.stream()
				.map(JobOpeningDTO::toDto)
				.collect(Collectors.toList());
		
		if(jobOpeningList != null) return jobOpeningList;
		return null;
	}
	
	//채용공고 상세
	public JobOpeningDTO jobOpeningDetail(int jobOpeningId) {
		
		JobOpeningDTO findJobOpening = jobRepo.findById(jobOpeningId)
				.map(JobOpeningDTO::toDto)
				.orElse(null);
		
		if(findJobOpening != null) return findJobOpening;
		return null;
	}
	
	//같은 회사의 채용공고 목록
	public List<JobOpeningDTO> jobOpeningListByCompany(int companyId) {
		
		List<JobOpeningDTO> jobOpeningList = jobRepo.findByCompany_CompanyId(companyId)
				.stream()
				.map(JobOpeningDTO::toDto)
				.collect(Collectors.toList());
		
		if(jobOpeningList != null) return jobOpeningList;
		return null;
	}

	//카테고리별 검색 목록
	public List<JobOpeningDTO> serch(String category, String keyword) {
		
		List<JobOpening> jobOpeningList = null;
		
		if(category.equals("all")) {
			jobOpeningList = jobRepo.findByAll(keyword);
		} else if (category.equals("position")) {
			jobOpeningList = jobRepo.findByPositionContaining(keyword);
		} else if (category.equals("contents")) {
			jobOpeningList = jobRepo.findByContentsContaining(keyword);
		} else {
			jobOpeningList = jobRepo.findBySkillContaining(keyword);
		}
		
		if(!jobOpeningList.isEmpty()) {
			return jobOpeningList.stream()
					.map(JobOpeningDTO::toDto)
					.collect(Collectors.toList());
		} else {
			return null;
		}
	}
	
	//유저 지원내역 목록
	public List<JobOpeningDTO> applicationList(String userId) {
		
		List<Application> applicationList = applyRepo.findByUser_UserId(userId);
		
		List<Integer> jobOpeningIds = applicationList
				.stream()
				.map(application -> application.getJobOpening().getJobOpeningId())
				.collect(Collectors.toList());
		
		List<JobOpeningDTO> jobOpeningList = jobRepo.findByJobOpeningIdIn(jobOpeningIds)
				.stream()
				.map(JobOpeningDTO::toDto)
				.collect(Collectors.toList());
		
		if(jobOpeningList != null) return jobOpeningList;
		return null;
	}
}
