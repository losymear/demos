package com.losyear.retrofit_swagger.rest;

import com.losyear.retrofit_swagger.dao.response.Httpbin_GET_Obj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @program: retrofit_swagger
 * @description: Http request to httpbin.org
 * @author: losymear
 * @create: 2018-10-20 09:47
 */
public interface HttpbinAPI {


    @GET("get")
    public Call<Httpbin_GET_Obj> testGET(@Query("param") String param);

}
