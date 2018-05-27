package co.jasonwyatt.asynclistutil_example.http.api;

import java.util.List;

import co.jasonwyatt.asynclistutil_example.http.baseresult.GankHttpResponse;
import co.jasonwyatt.asynclistutil_example.http.responseentity1.GankItemBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static co.jasonwyatt.asynclistutil_example.http.factory.ApiFactory.getApiService;

public interface TestService{

    public static final String HOST = "http://gank.io/api/";

    /**
     * 福利列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankHttpResponse<List<GankItemBean>>> getGirlList(@Path("num") int num, @Path("page") int page);

    public static TestService  getService(){
       return getApiService(TestService.class,HOST);
    }
}
