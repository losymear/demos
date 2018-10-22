package com.losyear.retrofit_swagger.DAO;

import com.losyear.retrofit_swagger.domaIn.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: retrofit_swagger
 * @description:
 * @author: losymear
 * @create: 2018-10-21 01:03
 */

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    People findDistinctByName(String name);

}
