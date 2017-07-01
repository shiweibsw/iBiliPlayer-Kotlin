package com.knightdavion.kotlin.ibiliplayer.model

/**
 * Created by shiwei on 2017/6/21.
 */
data class DiscoverTagModel(val list: List<Tag>, val trackid: Int)

data class Tag(val keyword: String, val status: String)
