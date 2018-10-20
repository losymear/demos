package com.losyear.retrofit_swagger.Controller;

import com.losyear.retrofit_swagger.dao.response.Httpbin_GET_Obj;
import com.losyear.retrofit_swagger.dao.response.Result;
import com.losyear.retrofit_swagger.rest.HttpbinAPIService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @program: retrofit_swagger
 * @description: Using httpbin.org
 * @author: losymear
 * @create: 2018-10-20 10:34
 */

@RestController
@Log4j2
public class HttpbinController {
    @Autowired
    private HttpbinAPIService httpbinAPI;

    /**
     * Global exception handler for Controller
     * see more about exception handler, read
     * https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-exceptionhandler
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handler(Exception ex) {
        return Result.error(1, ex.getMessage());
    }

    /**
     * test for retrofit @Query
     * See {@linktourl http://localhost:8080/testQuery?key=value}
     *
     * @param param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "request to httpbin.org/get",
            notes = "${HttpbinController.testQuery.notes}")
    @GetMapping("/testQuery")
    public Result testQuery(@ApiParam(value = "just a GET query param, no special meaning", required = true) @RequestParam("key") String param) throws Exception {
        Call<Httpbin_GET_Obj> call = httpbinAPI.testGET(param);
        Response<Httpbin_GET_Obj> executed = call.execute();
        log.info("body is {}", executed.body());
        return Result.ok(executed.body());
    }

    /**
     * test for retrofit @url
     * See {@linktourl http://localhost:8080/testUrl?key=value}
     *
     * @param param
     * @return
     * @throws Exception
     */
    @ApiImplicitParams(@ApiImplicitParam(name = "key", value = "a request param"))
    @GetMapping("/testUrl")
    public Result testGetWithUrl(@RequestParam("key") String param) throws Exception {
        Call<Httpbin_GET_Obj> call = httpbinAPI.testGETWithUrl("get", param);
        Response<Httpbin_GET_Obj> executed = call.execute();
        log.info("body is {}", executed.body());
        return Result.ok(executed.body());
    }


}
