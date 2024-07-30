package com.yeon.RecruitmentSite.dto;

import com.yeon.RecruitmentSite.entity.Company;
import com.yeon.RecruitmentSite.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
	
	private int companyId;
	private String companyName;
	private String country;
	private String region;

	public static CompanyDTO toDto(Company company) {
		if (company == null) return null;
		
		return CompanyDTO.builder()
				.companyId(company.getCompanyId())
				.companyName(company.getCompanyName())
				.country(company.getCountry())
				.region(company.getRegion())
				.build();
	}
	
	public Company toEntity() {
		
		return Company.builder()
				.companyId(companyId)
				.companyName(companyName)
				.country(country)
				.region(region)
				.build();
	}

}
