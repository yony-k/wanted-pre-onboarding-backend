package com.yeon.RecruitmentSite.jpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yeon.RecruitmentSite.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
