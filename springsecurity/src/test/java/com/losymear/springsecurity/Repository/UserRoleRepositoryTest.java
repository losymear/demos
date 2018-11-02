package com.losymear.springsecurity.Repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleRepositoryTest {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void test(){
        assertThat(userRoleRepository.findRolesByUserId("uuidadmin"), hasItems("ROLE1"));
    }

}