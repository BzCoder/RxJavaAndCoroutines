package me.bzcoder.coroutines_rxjava.rxjava

import io.reactivex.Observable
import me.bzcoder.coroutines_rxjava.entity.ProjectListEntity
import me.bzcoder.coroutines_rxjava.entity.ProjectTypeEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * 请求
 * @author: BaoZhou
 * @date : 2020/6/15 3:05 PM
 */
interface ListApiForRxjava2 {

    @GET("project/tree/json")
    fun getProjectList(): Observable<ProjectTypeEntity>

    //https://www.wanandroid.com/project/list/1/json?cid=294
    @GET("project/list/{page}/json")
    fun getProjectListDetail(
        @Path("page") page: Int,
        @Query("cid") cid: String
    ): Observable<ProjectListEntity>
}