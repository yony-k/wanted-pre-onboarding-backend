package com.yeon.RecruitmentSite.jpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yeon.RecruitmentSite.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	User findByUserIdAndUserPwd(String userId, String userPwd);
}
