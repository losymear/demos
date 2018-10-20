import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.Map;

/**
 * @program: retrofit_swagger
 * @description:
 * @author: losymear
 * @create: 2018-10-20 12:04
 */
@Log4j2
public class Example {

    public static void main(String[] args) throws Exception {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://httpbin.org")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        HttpbinApiService service = retrofit.create(HttpbinApiService.class);
        Call<HttpGetResult> call = service.testGET("param");
        Response<HttpGetResult> executed = call.execute();
        HttpGetResult httpGetResult = executed.body();
        log.info("result body {}", httpGetResult);


    }

    public interface HttpbinApiService {
        @GET("get")
         Call<HttpGetResult> testGET(@Query("param") String param);
    }


}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class HttpGetResult {
    public Map<String, String> args;
    public Map<String, String> headers;

}
