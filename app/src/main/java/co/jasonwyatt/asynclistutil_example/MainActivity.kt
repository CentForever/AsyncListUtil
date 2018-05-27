package co.jasonwyatt.asynclistutil_example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import co.jasonwyatt.asynclistutil_example.http.error.ExceptionHandle
import co.jasonwyatt.asynclistutil_example.http.model.TestModel
import co.jasonwyatt.asynclistutil_example.http.responseentity1.GankItemBean
import co.jasonwyatt.asynclistutil_example.http.util.HttpUtils
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AsyncAdapter
    private lateinit var itemSource: SQLiteItemSource
    var gsModel: TestModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)

        itemSource = SQLiteItemSource(getDatabase(this, "database.sqlite"))
        adapter = AsyncAdapter(itemSource, recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        gsModel = TestModel()

        gsModel!!.fetchGirlList(1, 1)
                .compose(HttpUtils.rxSchedulerHelper())
                .compose(HttpUtils.handleGankResult())
                .subscribe(io.reactivex.functions.Consumer {
                    apply {
                        Log.e("mgg", it.toString())
                    }
                }, io.reactivex.functions.Consumer {
                    Log.e("mgg", it.message)
                })
        TestModel().fetchGirlList(1, 2, object : co.jasonwyatt.asynclistutil_example.http.oberver.Observer<List<GankItemBean>>() {
            override fun OnSuccess(gankItemBeans: List<GankItemBean>) {
                Log.e("mengganggang", gankItemBeans.toString())
            }

            override fun OnFail(e: ExceptionHandle.ResponeThrowable) {

            }

            override fun OnCompleted() {

            }

            override fun OnDisposable(d: Disposable) {

            }
        })
    }

    override fun onStart() {
        super.onStart()
        adapter.onStart(recyclerView)
    }

    override fun onStop() {
        super.onStop()
        adapter.onStop(recyclerView)
    }
}

