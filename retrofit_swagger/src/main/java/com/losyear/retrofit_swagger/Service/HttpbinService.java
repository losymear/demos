package com.losyear.retrofit_swagger.Service;

import com.losyear.retrofit_swagger.dao.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: retrofit_swagger
 * @description:
 * @author: losymear
 * @create: 2018-10-20 10:05
 */

@RestController
public class HttpbinService {
    @GetMapping("/get")
    public Result httpbinTest_get() {
        return Result.ok("data");
    }

}
