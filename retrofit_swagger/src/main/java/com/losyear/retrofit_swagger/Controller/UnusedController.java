package com.losyear.retrofit_swagger.Controller;

import com.losyear.retrofit_swagger.dao.response.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeMap;

/**
 * @program: retrofit_swagger
 * @description: This Class is not unsed. I just test Spring MVC temporiraly.
 * @author: losymear
 * @create: 2018-10-20 10:05
 */

@RestController
public class UnusedController {

    @GetMapping("/test/okString")
    public Result testString(@RequestParam("string") String string) {
        return Result.ok("getString: " + string);
    }

    @GetMapping("/test/errorString")
    public Result testErrorString(@RequestParam("string") String string) {
        return Result.error(1, "get Error String: " + string);
    }

    @GetMapping("/test/json")
    public Result testJson(){
        TreeMap<String,String> map= new TreeMap<>();
        map.put("a", "b");
        return Result.ok(map);
    }

}
