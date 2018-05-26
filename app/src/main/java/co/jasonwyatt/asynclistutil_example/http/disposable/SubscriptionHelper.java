package co.jasonwyatt.asynclistutil_example.http.disposable;

import io.reactivex.disposables.Disposable;

public interface SubscriptionHelper<T> {
    void add(Disposable subscription);

    void cancel(Disposable t);

    void cancelall();
}
