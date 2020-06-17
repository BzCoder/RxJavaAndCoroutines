package me.bzcoder.coroutines_rxjava

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.bzcoder.coroutines_rxjava.ext.onClick
import me.bzcoder.coroutines_rxjava.ext.toast
import me.bzcoder.coroutines_rxjava.vm.MainViewModel

class MainActivity : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.successData.observe(this, androidx.lifecycle.Observer {
            toast(it.size.toString())
        })
        viewModel.successData2.observe(this, androidx.lifecycle.Observer {
            toast("列表的规模:" + it.size.toString())
        })
        btCoroutines.onClick {
            viewModel.coroutinesLoadListData()
        }
        btContinueCoroutines.onClick {
            viewModel.coroutinesLoadListDataDetail()
        }
        btZipRxjava2.onClick {
            viewModel.rxjava2ZipLoadListData(this)
        }
        btMergeRxjava2.onClick {
            viewModel.rxjava2MergeLoadListData(this)
        }
        btFlatmapRxjava2.onClick {
            viewModel.rxjava2LoadListDataDetail(this)
        }

        btFlatmap2Rxjava2.onClick {
            viewModel.rxjava2LoadListDataDetail2(this)
        }
    }
}
