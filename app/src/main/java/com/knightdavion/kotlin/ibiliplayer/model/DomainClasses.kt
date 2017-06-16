package com.knightdavion.kotlin.ibiliplayer.model

/**
 * Created by shiwei on 2017/6/15.
 */

data class TestBean(var totalPage: Int, var ps: Int, var pno: Int, var list: List<ListBean>) {
    val size: Int
        get() = list.size

    operator fun get(position: Int) = list[position]
}

data class ListBean(var id: String, var title: String, var source: String, var firstImg: String, var mark: String, var url: String)




