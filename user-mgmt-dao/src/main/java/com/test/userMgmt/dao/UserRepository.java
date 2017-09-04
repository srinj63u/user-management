package com.test.userMgmt.dao;

import com.test.userMgmt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sk11
 */
@Transactional(value="userMgmtTransactionManager")
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
		
}
