package com.yeon.RecruitmentSite.dto;

import com.yeon.RecruitmentSite.entity.ApplicationList;
import com.yeon.RecruitmentSite.entity.JobOpening;
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
public class ApplicationListDTO {

	private int applicationListId;
	private String userId;
	private int jobOpeningId;
	
	public static ApplicationListDTO toDto(ApplicationList applicationList) {
        if (applicationList == null) return null;
        
        return ApplicationListDTO.builder()
        		.applicationListId(applicationList.getApplicationListId())
        		.userId(applicationList.getUser().getUserId())
        		.jobOpeningId(applicationList.getJobOpening().getJobOpeningId())
        		.build();
    }
	
	public ApplicationList toEntity() {
		
		return ApplicationList.builder()
				.applicationListId(applicationListId)
				.user(User.builder().userId(userId).build())
				.jobOpening(JobOpening.builder().jobOpeningId(jobOpeningId).build())
				.build();
	}
}
