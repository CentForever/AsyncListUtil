package co.jasonwyatt.asynclistutil_example.http.manager;


import co.jasonwyatt.asynclistutil_example.http.api.TestService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private volatile static RetrofitManager retrofitManager;
    private Retrofit retrofit; //无参的单利模式

    public static RetrofitManager getSingleton() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                retrofitManager = new RetrofitManager();
            }
        }
        return retrofitManager;
    }

    //无参的构造方法
    private RetrofitManager() {
        initRetrofitManager();
    }

    //构造方法创建Retrofit实例
    private void initRetrofitManager() {
        retrofit = new Retrofit.Builder().baseUrl(TestService.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public TestService Apiservice() {
        return retrofit.create(TestService.class);
    }
}
