package com.knightdavion.kotlin.ibiliplayer.model

/**
 * Created by shiwei on 2017/6/15.
 */

data class GameCenterModle(val book_gift: List<BookGiftBean>, val game_list: List<GameListBean>)

data class BookGiftBean(val id: Int,
                        val name: String,
                        val image: String,
                        val link: String,
                        val book_status: Int)

data class GameListBean(val id: Int,
                        val title: String,
                        val summary: String,
                        val android_sign: String,
                        val android_pkg_name: String,
                        val android_pkg_size: Int,
                        val android_pkg_ver: Int,
                        val download_link: String,
                        val download_link2: String,
                        val icon: String,
                        val hot: Int,
                        val new: Int)

data class VipGameInfo(val imgPath: String, val link: String)
