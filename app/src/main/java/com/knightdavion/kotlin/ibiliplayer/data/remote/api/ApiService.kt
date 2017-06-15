package com.knightdavion.kotlin.ibiliplayer.data.remote.api

import com.knightdavion.kotlin.ibiliplayer.model.TestBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Created by shiwei on 2017/6/15.
 */
interface ApiService {

    companion object{
        val BASE_URL="http://v.juhe.cn/weixin/"
        val SUCCESS_CODE=0
    }

    @FormUrlEncoded
    @POST("query?key=7c2d1da3b8634a2b9fe8848c3a9edcba")
    fun getDatas(@Field("pno") pno: Int, @Field("ps") ps: Int, @Field("dtype") dtype: String): Observable<ApiResponse<TestBean>>
}