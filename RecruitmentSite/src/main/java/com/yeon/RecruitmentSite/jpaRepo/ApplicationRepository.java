package com.yeon.RecruitmentSite.jpaRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yeon.RecruitmentSite.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{

	boolean existsByUser_UserIdAndJobOpening_JobOpeningId(String userId, int jobOpeningId);
	Application findByUser_UserIdAndJobOpening_JobOpeningId(String userId, int jobOpeningId);
	List<Application> findByUser_UserId(String userId);
}
