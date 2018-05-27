package co.jasonwyatt.asynclistutil_example.http.util;

import co.jasonwyatt.asynclistutil_example.http.baseresult.GankHttpResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class HttpUtils {

    /**
     * Rx优雅处理服务器返回 * * @param <T> * @return
     */
    /*public <T> ObservableTransformer<HttpResult<T>, T> handleResult() {
        return upstream -> {
            return upstream.flatMap(result -> {
                if (result.isSuccess()) {
                    return createData(result.data);
                } else if (result.isTokenInvalid()) {
                    //处理token失效，tokenInvalid方法在BaseActivity 实现
                   // tokenInvalid();
                } else {
                    return Observable.error(new Exception(result.msg));
                }
                return Observable.empty();
            });
        };
    }

    private <T> Observable<T> createData(final T t) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(t);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }*/

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> ObservableTransformer<GankHttpResponse<T>, T> handleGankResult() {   //compose判断结果
        return new ObservableTransformer<GankHttpResponse<T>, T>() {
            @Override
            public Observable<T> apply(Observable<GankHttpResponse<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Function<GankHttpResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> apply(GankHttpResponse<T> tGankHttpResponse) {
                        if (!tGankHttpResponse.isError()) {
                            return createData(tGankHttpResponse.getResults());
                        } else {
                            return Observable.error(new Exception("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> observableEmitter) throws Exception {
                try {
                    observableEmitter.onNext(t);
                    observableEmitter.onComplete();
                } catch (Exception e) {
                    observableEmitter.onError(e);
                }
            }
        });
    }

}
