package com.knightdavion.kotlin.ibiliplayer.model

/**
 * Created by shiwei on 2017/6/19.
 */
data class SearchResultModle(val upuser: List<Upuser>, val bangumi: List<Bangumi>, val suggest: List<String>)

data class Upuser(val name: String)

data class Bangumi(val name: String)
