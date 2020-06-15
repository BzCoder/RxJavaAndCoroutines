package me.bzcoder.coroutines_rxjava

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import me.bzcoder.coroutines_rxjava.ext.onClick
import me.bzcoder.coroutines_rxjava.ext.toast
import me.bzcoder.coroutines_rxjava.rxjava.ListApiForRxjava2
import me.bzcoder.coroutines_rxjava.rxjava.RetrofitFactoryForRxjava2
import me.bzcoder.coroutines_rxjava.vm.MainViewModel

class MainActivity : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.successData.observe(this, androidx.lifecycle.Observer {
            toast(it.size.toString())
        })
        btCoroutines.onClick {
            viewModel.loadListData()
        }
        btRxjava2.onClick {
            RetrofitFactoryForRxjava2.instance
                .create(ListApiForRxjava2::class.java)
                .getProjectList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifecycle())
                .subscribe(object : Observer<ProjectTypeEntity> {
                    override fun onNext(it: ProjectTypeEntity) {
                        toast(it.data.size.toString())
                    }

                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }
                })


        }


    }
}
