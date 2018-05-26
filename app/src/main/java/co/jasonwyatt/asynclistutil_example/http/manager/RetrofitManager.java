package co.jasonwyatt.asynclistutil_example.http.manager;


import co.jasonwyatt.asynclistutil_example.http.api.TestService;

public class RetrofitManager {

    public static TestService getTestService() {
        return TestService.getService();
    }
}
