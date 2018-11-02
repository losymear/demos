package com.losymear.springsecurity.Repository;
import com.losymear.springsecurity.domain.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 19:10
 */



@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Integer> {

    @Query(value = "select t3.role from UserRoleEntity t1 inner join UserEntity t2 on t1.userId=t2.id inner join " +
            "RoleEntity t3 on t1.roleId=t3.id and t2.uuid=?1")
    List<String> findRolesByUserId(String userId);


}