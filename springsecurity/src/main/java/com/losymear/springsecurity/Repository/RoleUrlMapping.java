package com.losymear.springsecurity.Repository;

import com.google.common.collect.ImmutableMap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-03 10:32
 */

@Data
@Component
@Slf4j
@ConditionalOnProperty(name = "testFor", havingValue = "final")
public class RoleUrlMapping{
    private ImmutableMap<String, List> map;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;


    @PostConstruct
    public void initImmutableMap(){
        ImmutableMap.Builder builder = ImmutableMap.<String, ArrayList<String>>builder();

        roleRepository.findAll().stream().forEach(role->{
            System.out.println(role);
            String roleName = role.getRole();
            Integer roleId = role.getId();
            builder.put(roleName,userRoleRepository.findUrlPatternsByRoleId(roleId));
        });
        this.map = builder.build();
        System.out.println(this.map);
    }

}
