package me.bzcoder.coroutines_rxjava.coroutines

import io.reactivex.Observable
import me.bzcoder.coroutines_rxjava.ProjectTypeEntity
import retrofit2.http.GET


/**
 * 请求
 * @author: BaoZhou
 * @date : 2020/6/15 3:05 PM
 */
interface ListApiForCoroutines {

    @GET("project/tree/json")
    suspend fun getProjectList(): ProjectTypeEntity
}