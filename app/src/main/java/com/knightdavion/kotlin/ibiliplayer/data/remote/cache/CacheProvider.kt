package com.knightdavion.kotlin.ibiliplayer.data.remote.cache

import com.knightdavion.kotlin.ibiliplayer.data.remote.api.ApiResponse
import com.knightdavion.kotlin.ibiliplayer.model.TestBean
import io.reactivex.Observable
import io.rx_cache2.EvictProvider
import io.rx_cache2.LifeCache
import java.util.concurrent.TimeUnit


/**
 * Created by shiwei on 2017/6/15.
 */
interface CacheProvider {

    @LifeCache(duration = 10, timeUnit = TimeUnit.MINUTES)
    fun getDatas(oRepos: Observable<ApiResponse<TestBean>>, evictDynamicKey: EvictProvider): Observable<ApiResponse<TestBean>>
}