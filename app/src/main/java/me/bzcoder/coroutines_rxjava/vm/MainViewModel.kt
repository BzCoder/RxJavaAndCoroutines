package me.bzcoder.coroutines_rxjava.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.bzcoder.coroutines_rxjava.coroutines.ListApiForCoroutines
import me.bzcoder.coroutines_rxjava.coroutines.RetrofitFactoryForCoroutines
import me.bzcoder.coroutines_rxjava.entity.ProjectListEntity
import me.bzcoder.coroutines_rxjava.entity.ProjectTypeEntity
import me.bzcoder.coroutines_rxjava.rxjava.ListApiForRxjava2
import me.bzcoder.coroutines_rxjava.rxjava.RetrofitFactoryForRxjava2


/**
 *
 * @author: BaoZhou
 * @date : 2020/6/15 6:27 PM
 */
class MainViewModel : ViewModel() {
    var successData = MutableLiveData<List<ProjectTypeEntity.Data>>()
    var successData2 = MutableLiveData<List<ProjectListEntity.Data.Data>>()

    private val projectList: Observable<ProjectTypeEntity> by lazy {
        RetrofitFactoryForRxjava2.instance
            .create(ListApiForRxjava2::class.java)
            .getProjectList()
    }


    fun coroutinesLoadListData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val data1 = async {
                    RetrofitFactoryForCoroutines.instance
                        .create(ListApiForCoroutines::class.java)
                        .getProjectList().data
                }
                val data2 = async {
                    RetrofitFactoryForCoroutines.instance
                        .create(ListApiForCoroutines::class.java)
                        .getProjectList().data
                }
                withContext(Dispatchers.Main) {
                    successData.value = data1.await() + data2.await()
                }
            }
        }
    }

    fun coroutinesLoadListDataDetail() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val data1 =
                    RetrofitFactoryForCoroutines.instance
                        .create(ListApiForCoroutines::class.java)
                        .getProjectList().data
                val data2 =
                    RetrofitFactoryForCoroutines.instance
                        .create(ListApiForCoroutines::class.java)
                        .getProjectListDetail(1, data1[0].id)
                withContext(Dispatchers.Main) {
                    successData2.value = data2.data.datas
                }
            }
        }
    }

    //连续请求，第二个请求依赖第一个请求
    fun rxjava2LoadListDataDetail(activity: RxAppCompatActivity) {
        projectList.subscribeOn(Schedulers.io())
            .flatMap { it ->
                RetrofitFactoryForRxjava2.instance
                    .create(ListApiForRxjava2::class.java)
                    .getProjectListDetail(1, it.data[0].id)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .compose(activity.bindToLifecycle())
            .subscribe(object : Observer<ProjectListEntity> {
                override fun onNext(it: ProjectListEntity) {
                    successData2.value = it.data.datas

                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }

                override fun onSubscribe(d: Disposable) {
                }
            })
    }

    //把所有数据都请求了一次
    fun rxjava2LoadListDataDetail2(activity: RxAppCompatActivity) {
        projectList.subscribeOn(Schedulers.io())
            .map { it.data }
            .flatMap { it -> Observable.fromIterable(it) }
            .flatMap {
                RetrofitFactoryForRxjava2.instance
                    .create(ListApiForRxjava2::class.java)
                    .getProjectListDetail(1, it.id)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .compose(activity.bindToLifecycle())
            .subscribe(object : Observer<ProjectListEntity> {
                override fun onNext(it: ProjectListEntity) {
                    successData2.value = it.data.datas
                }

                override fun onComplete() {
                }


                override fun onError(e: Throwable) {
                }

                override fun onSubscribe(d: Disposable) {
                }
            })
    }

    fun rxjava2ZipLoadListData(activity: RxAppCompatActivity) {
        Observable.zip(projectList, projectList,
            BiFunction<ProjectTypeEntity, ProjectTypeEntity, List<ProjectTypeEntity.Data>> { i1, i2 ->
                i1.data + i2.data
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(activity.bindToLifecycle())
            .subscribe(object : Observer<List<ProjectTypeEntity.Data>> {
                override fun onNext(it: List<ProjectTypeEntity.Data>) {
                    successData.value = it
                }

                override fun onComplete() {
                }


                override fun onError(e: Throwable) {
                }

                override fun onSubscribe(d: Disposable) {
                }
            })
    }


    fun rxjava2MergeLoadListData(activity: RxAppCompatActivity) {
        Observable.merge(projectList, projectList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(activity.bindToLifecycle())
            .subscribe(object : Observer<ProjectTypeEntity> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: ProjectTypeEntity) {
                    successData.value = t.data
                }

                override fun onError(e: Throwable) {
                }

            })

    }
}