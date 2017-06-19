package com.knightdavion.kotlin.ibiliplayer.data.remote.cache

import com.knightdavion.kotlin.ibiliplayer.data.remote.api.ApiResponse
import com.knightdavion.kotlin.ibiliplayer.model.LiveHomeTypeModel
import com.knightdavion.kotlin.ibiliplayer.model.VipGameInfo
import io.reactivex.Observable
import io.rx_cache2.EvictProvider
import io.rx_cache2.LifeCache
import java.util.concurrent.TimeUnit


/**
 * Created by shiwei on 2017/6/15.
 */
interface CacheProvider {

    companion object {
        const val CACHE_DURATION: Long = 1L
    }

    @LifeCache(duration = CACHE_DURATION, timeUnit = TimeUnit.HOURS)
    fun getLiveHomeTypes(oRepos: Observable<ApiResponse<List<LiveHomeTypeModel>>>, evictDynamicKey: EvictProvider): Observable<ApiResponse<List<LiveHomeTypeModel>>>

    @LifeCache(duration = CACHE_DURATION, timeUnit = TimeUnit.HOURS)
    fun getVipGameInfo(oRepos: Observable<ApiResponse<VipGameInfo>>, evictDynamicKey: EvictProvider): Observable<ApiResponse<VipGameInfo>>
}