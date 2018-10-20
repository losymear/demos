package com.losyear.retrofit_swagger.domaIn;


import lombok.Data;

import javax.persistence.*;

/**
 * @program: retrofit_swagger
 * @description:
 * @author: losymear
 * @create: 2018-10-21 00:06
 */

@Entity
@Data
@Table(name = "people")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;

    //...
}
