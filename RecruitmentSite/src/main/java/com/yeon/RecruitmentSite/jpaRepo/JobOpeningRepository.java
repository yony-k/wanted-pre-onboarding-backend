package com.yeon.RecruitmentSite.jpaRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yeon.RecruitmentSite.entity.JobOpening;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Integer>{
	
	List<JobOpening> findByCompany_CompanyId(int companyId);
	List<JobOpening> findByPositionContaining(String keyword);
	List<JobOpening> findByContentsContaining(String keyword);
	
	@Query(value = "SELECT * FROM job_opening j WHERE JSON_CONTAINS(j.skill, :skill, '$')", nativeQuery = true)
	List<JobOpening> findBySkillContaining(@Param("skill") String keyword);
}
