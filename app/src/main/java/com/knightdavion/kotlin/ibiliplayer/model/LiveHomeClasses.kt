package com.knightdavion.kotlin.ibiliplayer.model

/**
 * Created by shiwei on 2017/6/17.
 */
data class LiveHomeModel(val banner: List<Banner>, val partitions: List<Partitions>)

data class Partitions(val partition: Partition, val lives: MutableList<Lives>)

data class Partition(val id: Int, val name: String, val area: String, val sub_icon: SubIcon, val count: Int)

data class SubIcon(val src: String, val height: String, val width: String)

data class Lives(val owner: Owner, val cover: Cover, val title: String, val room_id: Int, val check_version: Int, val online: Int, val area: String, val area_id: Int, val playurl: String, val is_tv: Int, val broadcast_type: Int, val accept_quality: Int)

data class Owner(val face: String, val mid: Int, val name: String)

data class Cover(val src: String, val height: Int, val width: Int)

data class Banner(val img: String, val link: String, val remark: String, val title: String)

//推荐主播数据
data class LiveHomeHotModle(var recommend_data: RecommendData)

data class RecommendData(val banner_data: List<BannerData>, var lives: MutableList<Lives>, var partition: Partition)

data class BannerData(val cover: Cover, val is_clip: Int, val title: String)