package me.bzcoder.coroutines_rxjava


import com.google.gson.annotations.SerializedName

data class ProjectTypeEntity(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("errorCode")
    val errorCode: Int, // 0
    @SerializedName("errorMsg")
    val errorMsg: String) {
    data class Data(
        @SerializedName("children")
        val children: List<Any>,
        @SerializedName("courseId")
        val courseId: Int, // 13
        @SerializedName("id")
        val id: Int, // 294
        @SerializedName("name")
        val name: String, // 完整项目
        @SerializedName("order")
        val order: Int, // 145000
        @SerializedName("parentChapterId")
        val parentChapterId: Int, // 293
        @SerializedName("userControlSetTop")
        val userControlSetTop: Boolean, // false
        @SerializedName("visible")
        val visible: Int // 0
    )
}