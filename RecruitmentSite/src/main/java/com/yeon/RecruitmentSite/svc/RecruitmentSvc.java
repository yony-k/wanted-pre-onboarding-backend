package com.yeon.RecruitmentSite.svc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeon.RecruitmentSite.dto.JobOpeningDTO;
import com.yeon.RecruitmentSite.jpaRepo.JobOpeningRepository;

@Service
public class RecruitmentSvc {
	
	@Autowired
	private JobOpeningRepository jobOpeningRepo;
	
	public List<JobOpeningDTO> jobOpeningList() {
		
		List<JobOpeningDTO> jobOpeningList = jobOpeningRepo.findAll()
				.stream()
				.map(JobOpeningDTO::toDto)
				.collect(Collectors.toList());
		
		if(jobOpeningList != null) return jobOpeningList;
		return null;
	}
}
