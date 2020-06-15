package me.bzcoder.coroutines_rxjava.rxjava

import io.reactivex.Observable
import me.bzcoder.coroutines_rxjava.ProjectTypeEntity
import retrofit2.http.GET



/**
 * 请求
 * @author: BaoZhou
 * @date : 2020/6/15 3:05 PM
 */
interface ListApiForRxjava2 {

    @GET("project/tree/json")
    fun getProjectList (): Observable<ProjectTypeEntity>

}