package com.losyear.retrofit_swagger.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @program: retrofit_swagger
 * @description:
 * @author: losymear
 * @create: 2018-10-20 10:49
 */

@Configuration
public class HttpbinRestConfig {
    @Bean
    public HttpbinAPIService httpbinAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://httpbin.org")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(HttpbinAPIService.class);
    }

}
