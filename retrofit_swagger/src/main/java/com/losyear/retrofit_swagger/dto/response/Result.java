package com.losyear.retrofit_swagger.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: retrofit_swagger
 * @description:
 * @author: losymear
 * @create: 2018-10-20 10:06
 */


@Getter
@Setter

// TODO: use Enum for errCode
public class Result<T> {

    private Object data;
    private Integer errCode = 0;
    private String errMsg;


    /**
     * Spring handled http request successfully, return response data wrapped in a Result class
     * @param data response data to http request
     * @param <T> type for data
     * @return
     */
    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<T>();
        r.data = data;
        return r;
    }

    /**
     * Spring handling http request failed(for any reason), return errCode and errMsg wrapped in a Result class
     * @param errCode
     * @param errMsg
     * @return
     */
    public static  Result error(Integer errCode, String errMsg) {
        Result r = new Result<>();
        r.errCode = errCode;
        r.errMsg = errMsg;
        return r;
    }

    /**
     * Sometimes it's useful to send some data when besides errCode and errMsg
     * @param errCode
     * @param errMsg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(Integer errCode, String errMsg, T data) {
        Result r = new Result<>();
        r.errCode = errCode;
        r.errMsg = errMsg;
        r.data = data;
        return r;
    }


}
