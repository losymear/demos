package com.losymear.springsecurity.Repository;

import com.losymear.springsecurity.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-03 10:49
 */

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {

    @Override
    List<RoleEntity> findAll();
}