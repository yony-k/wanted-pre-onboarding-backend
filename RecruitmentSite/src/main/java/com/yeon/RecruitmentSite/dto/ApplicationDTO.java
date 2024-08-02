package com.yeon.RecruitmentSite.dto;

import com.yeon.RecruitmentSite.entity.Application;
import com.yeon.RecruitmentSite.entity.JobOpening;
import com.yeon.RecruitmentSite.entity.User;

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
public class ApplicationDTO {

	private int applicationId;
	private String userId;
	private int jobOpeningId;
	
	public static ApplicationDTO toDto(Application applicationList) {
        if (applicationList == null) return null;
        
        return ApplicationDTO.builder()
        		.applicationId(applicationList.getApplicationListId())
        		.userId(applicationList.getUser().getUserId())
        		.jobOpeningId(applicationList.getJobOpening().getJobOpeningId())
        		.build();
    }
	
	public Application toEntity() {
		
		return Application.builder()
				.applicationListId(applicationId)
				.user(User.builder().userId(userId).build())
				.jobOpening(JobOpening.builder().jobOpeningId(jobOpeningId).build())
				.build();
	}
}
