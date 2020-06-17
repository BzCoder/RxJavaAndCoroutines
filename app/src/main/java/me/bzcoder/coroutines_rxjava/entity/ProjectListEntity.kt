package me.bzcoder.coroutines_rxjava.entity


import com.google.gson.annotations.SerializedName

data class ProjectListEntity(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("errorCode")
    val errorCode: Int, // 0
    @SerializedName("errorMsg")
    val errorMsg: String
) {
    data class Data(
        @SerializedName("curPage")
        val curPage: Int, // 1
        @SerializedName("datas")
        val datas: List<Data>,
        @SerializedName("offset")
        val offset: Int, // 0
        @SerializedName("over")
        val over: Boolean, // false
        @SerializedName("pageCount")
        val pageCount: Int, // 12
        @SerializedName("size")
        val size: Int, // 15
        @SerializedName("total")
        val total: Int // 177
    ) {
        data class Data(
            @SerializedName("apkLink")
            val apkLink: String,
            @SerializedName("audit")
            val audit: Int, // 1
            @SerializedName("author")
            val author: String, // winlee28
            @SerializedName("canEdit")
            val canEdit: Boolean, // false
            @SerializedName("chapterId")
            val chapterId: Int, // 294
            @SerializedName("chapterName")
            val chapterName: String, // 完整项目
            @SerializedName("collect")
            val collect: Boolean, // false
            @SerializedName("courseId")
            val courseId: Int, // 13
            @SerializedName("desc")
            val desc: String, // Kotlin+Jetpack+Coroutines+Retrofit+koin
            @SerializedName("descMd")
            val descMd: String,
            @SerializedName("envelopePic")
            val envelopePic: String, // https://wanandroid.com/resources/image/pc/default_project_img.jpg
            @SerializedName("fresh")
            val fresh: Boolean, // false
            @SerializedName("id")
            val id: Int, // 13740
            @SerializedName("link")
            val link: String, // https://www.wanandroid.com/blog/show/2767
            @SerializedName("niceDate")
            val niceDate: String, // 2020-06-02 21:59
            @SerializedName("niceShareDate")
            val niceShareDate: String, // 2020-06-02 21:58
            @SerializedName("origin")
            val origin: String,
            @SerializedName("prefix")
            val prefix: String,
            @SerializedName("projectLink")
            val projectLink: String, // https://github.com/winlee28/Jetpack-WanAndroid
            @SerializedName("publishTime")
            val publishTime: Long, // 1591106340000
            @SerializedName("realSuperChapterId")
            val realSuperChapterId: Int, // 293
            @SerializedName("selfVisible")
            val selfVisible: Int, // 0
            @SerializedName("shareDate")
            val shareDate: Long, // 1591106307000
            @SerializedName("shareUser")
            val shareUser: String,
            @SerializedName("superChapterId")
            val superChapterId: Int, // 294
            @SerializedName("superChapterName")
            val superChapterName: String, // 开源项目主Tab
            @SerializedName("tags")
            val tags: List<Tag>,
            @SerializedName("title")
            val title: String, // Jetpack架构开发组件化应用实战
            @SerializedName("type")
            val type: Int, // 0
            @SerializedName("userId")
            val userId: Int, // -1
            @SerializedName("visible")
            val visible: Int, // 0
            @SerializedName("zan")
            val zan: Int // 0
        ) {
            data class Tag(
                @SerializedName("name")
                val name: String, // 项目
                @SerializedName("url")
                val url: String // /project/list/1?cid=294
            )
        }
    }
}