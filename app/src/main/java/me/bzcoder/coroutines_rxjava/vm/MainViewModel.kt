package me.bzcoder.coroutines_rxjava.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.bzcoder.coroutines_rxjava.ProjectTypeEntity
import me.bzcoder.coroutines_rxjava.coroutines.ListApiForCoroutines
import me.bzcoder.coroutines_rxjava.coroutines.RetrofitFactoryForCoroutines


/**
 *
 * @author: BaoZhou
 * @date : 2020/6/15 6:27 PM
 */
class MainViewModel : ViewModel() {
    var successData = MutableLiveData<List<ProjectTypeEntity.Data>>()


    fun loadListData() {
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
}