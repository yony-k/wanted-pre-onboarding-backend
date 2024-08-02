package com.yeon.RecruitmentSite.svc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeon.RecruitmentSite.dto.CompanyDTO;
import com.yeon.RecruitmentSite.dto.JobOpeningDTO;
import com.yeon.RecruitmentSite.dto.LoginDTO;
import com.yeon.RecruitmentSite.dto.UserDTO;
import com.yeon.RecruitmentSite.entity.Company;
import com.yeon.RecruitmentSite.entity.JobOpening;
import com.yeon.RecruitmentSite.entity.User;
import com.yeon.RecruitmentSite.jpaRepo.ApplicationRepository;
import com.yeon.RecruitmentSite.jpaRepo.JobOpeningRepository;
import com.yeon.RecruitmentSite.jpaRepo.UserRepository;

@Service
public class LoginSvc {

	@Autowired
	private UserRepository usersRepository;
	
	//로그인 메소드
	public UserDTO login(LoginDTO loginDTO) {
		
		User findUser = usersRepository.findByUserIdAndUserPwd
				(loginDTO.getUserId(), loginDTO.getUserPwd());
		if(findUser != null) return UserDTO.toDto(findUser);
		return null;
	}
	
}
