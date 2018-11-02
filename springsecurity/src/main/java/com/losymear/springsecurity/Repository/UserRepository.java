package com.losymear.springsecurity.Repository;

import com.losymear.springsecurity.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 13:36
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findDistinctByUuid(String uuid);

}