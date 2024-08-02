package com.yeon.RecruitmentSite.dto;

import java.util.List;
import java.util.Optional;

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
public class UserDTO {
	
	private String userId;
	private String userName;
	private String userPwd;
	private int companyId;
	private String userType;
	
	public static UserDTO toDto(User user) {
		
		return UserDTO.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.userPwd(user.getUserPwd())
				.companyId(Optional.ofNullable(user.getCompany())
                        .map(Company::getCompanyId)
                        .orElse(0)) 
				.userType(user.getUserType())
				.build();
	}
	
	public User toEntity() {
		
		Company company = (companyId != 0) 
				? Company.builder().companyId(companyId).build() 
				: null;
		
		return User.builder()
				.userId(userId)
				.userName(userName)
				.userPwd(userPwd)
				.company(company)
				.userType(userType)
				.build();
	}

}
