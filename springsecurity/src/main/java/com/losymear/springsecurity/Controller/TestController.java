package com.losymear.springsecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-10-31 17:13
 */

@RestController
public class TestController {

    @GetMapping("/test/permitDefault/string")
    public String getString1(){
        return "permitDefault";
    }



    @GetMapping("/test/anyUser/string")
    public String getString2(){
        return "anyUser";
    }

    @GetMapping("/test/userDBA/string")
    public String getString3(){
        return "userDBA";
    }
}
