package com.knightdavion.kotlin.ibiliplayer.data.remote

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.knightdavion.kotlin.ibiliplayer.App
import com.knightdavion.kotlin.ibiliplayer.data.remote.api.ApiResponse
import com.knightdavion.kotlin.ibiliplayer.data.remote.api.ApiService
import com.knightdavion.kotlin.ibiliplayer.data.remote.cache.CacheProvider
import com.knightdavion.kotlin.ibiliplayer.data.remote.parser.GsonTSpeaker
import com.knightdavion.kotlin.ibiliplayer.model.TestBean
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.rx_cache2.EvictProvider
import io.rx_cache2.internal.RxCache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by shiwei on 2017/6/15.
 */
object HttpManager {

    var mRetrofit: Retrofit? = null
    var mApiService: ApiService? = null
    var cacheProvider: CacheProvider? = null
    val DEFAULT_TIMEOUT: Long = 5L

    init {
        val level = HttpLoggingInterceptor.Level.BODY
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.i("HttpManager", message) })
        loggingInterceptor.level = level;
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
        val okHttpClient = builder.build()

        mRetrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.BASE_URL)
                .client(okHttpClient)
                .build()
        cacheProvider = RxCache.Builder()
                .persistence(App.instance.filesDir, GsonTSpeaker())
                .using(CacheProvider::class.java)
        mApiService = mRetrofit?.create(ApiService::class.java)
    }

    fun <T> toSubscribe(o: Observable<ApiResponse<T>>, s: Observer<T>) {
        o.subscribeOn(Schedulers.io())
                .map(Function<ApiResponse<T>, T>() { response ->
                    val code = Integer.parseInt(response.getCode())
                    if (code != ApiService.SUCCESS_CODE) {
                        throw ApiException(code, response.getMsg())
                    } else {
                        response.getDatas()
                    }
                }).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    fun getDatasWithCache(subscriber: Observer<TestBean>, pno: Int, ps: Int, dtype: String, update: Boolean) {
        toSubscribe(cacheProvider!!.getDatas(mApiService!!.getDatas(pno, ps, dtype), EvictProvider(update)), subscriber)
    }
}
