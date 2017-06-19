package com.knightdavion.kotlin.ibiliplayer.data.remote

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.knightdavion.kotlin.ibiliplayer.App
import com.knightdavion.kotlin.ibiliplayer.data.remote.api.ApiResponse
import com.knightdavion.kotlin.ibiliplayer.data.remote.api.ApiService
import com.knightdavion.kotlin.ibiliplayer.data.remote.cache.CacheProvider
import com.knightdavion.kotlin.ibiliplayer.data.remote.parser.GsonTSpeaker
import com.knightdavion.kotlin.ibiliplayer.model.*
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
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
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit


/**
 * Created by shiwei on 2017/6/15.
 */
object HttpManager {

    var mRetrofit: Retrofit? = null
    var mApiService: ApiService? = null
    var cacheProvider: CacheProvider? = null
    var okHttpClient: OkHttpClient? = null
    val DEFAULT_TIMEOUT: Long = 5L

    init {
        val level = HttpLoggingInterceptor.Level.BODY
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.i("HttpManager", message) })
        loggingInterceptor.level = level;
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
        okHttpClient = builder.build()

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

    /**
     * 获取游戏中心头部Banner数据
     */
    fun getVipGameInfo(subscriber: Observer<VipGameInfo>, update: Boolean) {
        toSubscribe(cacheProvider!!.getVipGameInfo(mApiService!!.getVipGameInfo(), EvictProvider(update)), subscriber)
    }

    /**
     * 获取直播分类
     */
    fun getLiveHomeTypes(subscriber: Observer<List<LiveHomeTypeModel>>, update: Boolean) {
        toSubscribe(cacheProvider!!.getLiveHomeTypes(mApiService!!.getLiveHomeTypes(), EvictProvider(update)), subscriber)
    }

    /**
     * 获取直播各个区域数据
     */
    fun getLiveHomeDatas(subscriber: Observer<LiveHomeModel>) {
        toSubscribe(mApiService!!.getLiveHomeDatas(), subscriber)
    }

    /**
     * 获取推荐主播数据
     */
    fun getLiveHomeHotDatas(subscriber: Observer<LiveHomeHotModle>) {
        toSubscribe(mApiService!!.getLiveHomeHotDatas(), subscriber)
    }

    /**
     * 获取实时搜索建议
     */
    fun getSearchSuggests(subscriber: Observer<SearchResultModle>, keyword: String) {
        toSubscribe(mApiService!!.getSearchSuggests(keyword), subscriber)
    }

    /**
     * 获取游戏中心数据（本地）
     */
    fun getGameList(subscriber: Observer<GameCenterModle>, context: Context, url: String) {
        Observable.create(ObservableOnSubscribe<GameCenterModle> {
            val result = Gson().fromJson(readAssetsJson(context, url), GameCenterModle::class.java)
            it.onNext(result)
        }).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    private fun <T> toSubscribe(o: Observable<ApiResponse<T>>, s: Observer<T>) {
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

    /**
     * 获取assets目录下的json
     */
    private fun readAssetsJson(context: Context, url: String): String? {
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open(url)
            val br = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            stringBuilder.append("{")
            var str: String
            while (br.read() != -1) {
                str = br.readLine() ?: ""
                if (!TextUtils.isEmpty(str)) {
                    stringBuilder.append(str.trim())
                }
            }
            stringBuilder.append("}")
            return stringBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}
