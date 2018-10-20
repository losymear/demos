package com.losyear.retrofit_swagger.Controller;

import com.losyear.retrofit_swagger.dao.response.Result;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.GET;

/**
 * @program: retrofit_swagger
 * @description:
 * @author: losymear
 * @create: 2018-10-20 10:05
 */

@RestController
public class HttpbinController {

    @GET("testString")
    public Result testString(@RequestParam("string") String string) {
        return Result.ok(string);
    }

}
