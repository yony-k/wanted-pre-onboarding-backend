package com.yeon.RecruitmentSite.dto;

import java.util.List;

import com.yeon.RecruitmentSite.entity.Company;
import com.yeon.RecruitmentSite.entity.JobOpening;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningDTO {
	
	private int jobOpeningId;
	private CompanyDTO company;
	private String position;
	private int salary;
	private String contents;
	private List<String> skill;
	
	public static JobOpeningDTO toDto(JobOpening jobOpening) {
		if(jobOpening == null) return null;
		
		return JobOpeningDTO.builder()
				.jobOpeningId(jobOpening.getJobOpeningId())
				.company(CompanyDTO.toDto(jobOpening.getCompany()))
				.position(jobOpening.getPosition())
				.salary(jobOpening.getSalary())
				.contents(jobOpening.getContents())
				.skill(jobOpening.getSkill())
				.build();
	}
	
	public JobOpening toEntity() {
		return JobOpening.builder()
				.jobOpeningId(jobOpeningId)
				.company(Company.builder().companyId(company.getCompanyId()).build())
				.position(position)
				.salary(salary)
				.contents(contents)
				.skill(skill)
				.build();
	}
}
