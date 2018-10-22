package com.losyear.retrofit_swagger.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

/**
 * @program: retrofit_swagger
 * @description:
 * @author: losymear
 * @create: 2018-10-20 10:03
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Httpbin_GET_Obj {
    public Map<String, String> args;
    public Map<String, String> headers;

}


