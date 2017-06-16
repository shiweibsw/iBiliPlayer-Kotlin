package com.knightdavion.kotlin.ibiliplayer.data.remote.api

import com.knightdavion.kotlin.ibiliplayer.model.LiveTypeModel
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
        const val LIVE_BASE_URL = "http://api.live.bilibili.com/"
        val SUCCESS_CODE = 0
    }

    @FormUrlEncoded
    @POST("query?key=7c2d1da3b8634a2b9fe8848c3a9edcba")
    fun getDatas(@Field("pno") pno: Int, @Field("ps") ps: Int, @Field("dtype") dtype: String): Observable<ApiResponse<TestBean>>


    @GET("/api/v1/games/gift?appkey=1d8b6e7d45233436&build=507000&mobi_app=android&platform=android&ts=1497577153&sign=04071b4ef7641f1ad5394a3e5c3b03f6")
    fun getVipGameInfo(): Observable<ApiResponse<VipGameInfo>>

    @GET(LIVE_BASE_URL + "AppIndex/areas?_device=android&_hwid=12f957357901e986&access_key=58b319d3823d18b9122b53588628bbc3&appkey=1d8b6e7d45233436&build=507000&mobi_app=android&platform=android&scale=xxhdpi&src=huawei&trace_id=20170616163000054&ts=1497601854&version=5.7.0.507000&sign=eced774d0c8b76a723f6f59b210e6b93")
    fun getLiveTyps(): Observable<ApiResponse<List<LiveTypeModel>>>
}