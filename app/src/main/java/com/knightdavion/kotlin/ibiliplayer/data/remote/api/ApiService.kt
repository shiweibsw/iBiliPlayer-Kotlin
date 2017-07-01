package com.knightdavion.kotlin.ibiliplayer.data.remote.api

import com.knightdavion.kotlin.ibiliplayer.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by shiwei on 2017/6/15.
 */
interface ApiService {

    companion object {
        val BASE_URL = "http://vip.bilibili.com/"
        const val LIVE_BASE_URL = "http://api.live.bilibili.com/"
        const val APP_BASE_URL = "http://app.bilibili.com/"
        val SUCCESS_CODE = 0
    }

    @GET("/api/v1/games/gift?appkey=1d8b6e7d45233436&build=507000&mobi_app=android&platform=android&ts=1497577153&sign=04071b4ef7641f1ad5394a3e5c3b03f6")
    fun getVipGameInfo(): Observable<ApiResponse<VipGameInfo>>

    @GET(LIVE_BASE_URL + "AppIndex/areas?_device=android&_hwid=12f957357901e986&access_key=58b319d3823d18b9122b53588628bbc3&appkey=1d8b6e7d45233436&build=507000&mobi_app=android&platform=android&scale=xxhdpi&src=huawei&trace_id=20170616163000054&ts=1497601854&version=5.7.0.507000&sign=eced774d0c8b76a723f6f59b210e6b93")
    fun getLiveHomeTypes(): Observable<ApiResponse<List<LiveHomeTypeModel>>>

    @GET(LIVE_BASE_URL + "AppNewIndex/common?_device=android&_hwid=12f957357901e986&appkey=1d8b6e7d45233436&build=507000&mobi_app=android&platform=android&scale=xxhdpi&src=huawei&trace_id=20170617093900057&ts=1497663597&version=5.7.0.507000&sign=21c9329af4ec203dde85560275d05590")
    fun getLiveHomeDatas(): Observable<ApiResponse<LiveHomeModel>>

    @GET(LIVE_BASE_URL + "AppNewIndex/recommend?_device=android&_hwid=12f957357901e986&appkey=1d8b6e7d45233436&build=507000&mobi_app=android&platform=android&scale=xxhdpi&src=huawei&trace_id=20170617142600035&ts=1497680795&version=5.7.0.507000&sign=e0e93fcc42a2dbd83c44f13f6b552224")
    fun getLiveHomeHotDatas(): Observable<ApiResponse<LiveHomeHotModle>>

    @GET(APP_BASE_URL + "x/v2/search/suggest?appkey=1d8b6e7d45233436&build=506000&mobi_app=android&platform=android&ts=1497858075&type=accurate&sign=eca44810bac5172ba752568c050cd623")
    fun getSearchSuggests(@Query("keyword") keyword: String): Observable<ApiResponse<SearchResultModle>>

    @GET(APP_BASE_URL + "x/v2/search/hot?appkey=1d8b6e7d45233436&build=506000&limit=50&mobi_app=android&platform=android&ts=1498030948&sign=5cd36761be9e593a393a4abcf66e294e")
    fun getDiscoverTagList(): Observable<ApiResponse<DiscoverTagModel>>
}