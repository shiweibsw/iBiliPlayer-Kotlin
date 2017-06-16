package com.knightdavion.kotlin.ibiliplayer.data.remote.api

import com.knightdavion.kotlin.ibiliplayer.model.TestBean
import com.knightdavion.kotlin.ibiliplayer.model.VipGameInfo
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Created by shiwei on 2017/6/15.
 */
interface ApiService {

    companion object {
        val BASE_URL = "http://vip.bilibili.com/"
        val SUCCESS_CODE = 0
    }

    @FormUrlEncoded
    @POST("query?key=7c2d1da3b8634a2b9fe8848c3a9edcba")
    fun getDatas(@Field("pno") pno: Int, @Field("ps") ps: Int, @Field("dtype") dtype: String): Observable<ApiResponse<TestBean>>


    @GET("/api/v1/games/gift?appkey=1d8b6e7d45233436&build=507000&mobi_app=android&platform=android&ts=1497577153&sign=04071b4ef7641f1ad5394a3e5c3b03f6")
    fun getVipGameInfo(): Observable<ApiResponse<VipGameInfo>>


}