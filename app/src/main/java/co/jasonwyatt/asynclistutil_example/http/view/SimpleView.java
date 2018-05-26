package co.jasonwyatt.asynclistutil_example.http.view;

import co.jasonwyatt.asynclistutil_example.http.error.ExceptionHandle;

public interface SimpleView {
    void onSuccess(Object object);

    void onFail(ExceptionHandle.ResponeThrowable t);

    void OnCompleted();
}
