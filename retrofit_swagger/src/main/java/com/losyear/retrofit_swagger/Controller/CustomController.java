package com.losyear.retrofit_swagger.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: retrofit_swagger
 * @description:
 * @author: losymear
 * @create: 2018-10-20 18:41
 */

@RestController
public class CustomController {


    @RequestMapping(value = "/custom", method = RequestMethod.POST)
    public String custom() {
        return "custom";
    }
}

