package com.losyear.retrofit_swagger.rest;

import com.losyear.retrofit_swagger.dao.response.Httpbin_GET_Obj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @program: retrofit_swagger
 * @description: Http request to httpbin.org
 * @author: losymear
 * @create: 2018-10-20 09:47
 */
public interface HttpbinAPIService {
    @GET("get")
    Call<Httpbin_GET_Obj> testGET(@Query("param") String param);

    @GET
    Call<Httpbin_GET_Obj> testGETWithUrl(@Url String url, @Query("param") String param);

}
