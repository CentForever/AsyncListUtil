package co.jasonwyatt.asynclistutil_example.http.model;

import java.util.List;

import co.jasonwyatt.asynclistutil_example.http.baseresult.GankHttpResponse;
import co.jasonwyatt.asynclistutil_example.http.responseentity.GankItemBean;
import co.jasonwyatt.asynclistutil_example.http.manager.RetrofitManager;
import co.jasonwyatt.asynclistutil_example.http.oberver.Observer;
import co.jasonwyatt.asynclistutil_example.http.util.HttpUtils;
import io.reactivex.Observable;

public class TestModel {
    public Observable<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page) {
        return RetrofitManager.getTestService().getGirlList(num, page);
    }

    public void fetchGirlList(int num, int page, Observer<List<GankItemBean>> observer) {
        RetrofitManager.getTestService().getGirlList(num, page)
                .compose(HttpUtils.<GankHttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(HttpUtils.<List<GankItemBean>>handleGankResult())
                .subscribe(observer);
    }
}
