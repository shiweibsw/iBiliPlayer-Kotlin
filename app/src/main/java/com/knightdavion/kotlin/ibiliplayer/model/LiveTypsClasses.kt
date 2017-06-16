package com.knightdavion.kotlin.ibiliplayer.model

/**
 * Created by shiwei on 2017/6/16.
 */
data class LiveTypeModel(val id: Int, val name: String, val entrance_icon: EntranceIcon)

data class EntranceIcon(val height: Int, val src: String, val width: Int)