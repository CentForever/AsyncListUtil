package co.jasonwyatt.asynclistutil_example.http.baseresult;

import java.io.Serializable;

public class GankHttpResponse<T> implements Serializable {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
