package com.sol.kreditbee.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.sol.kreditbee.R
import com.sol.kreditbee.base.BaseActivity
import javax.inject.Inject
import com.sol.kreditbee.data.Result
import com.sol.kreditbee.data.repository.local.Album
import com.sol.kreditbee.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        getAlbums()
    }

    private fun getAlbums() {
        mainViewModel.getAlbums.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    Log.d("Albums ", "${Gson().toJson(result.data)}")
                    setUpTabsAndViewPager(result.data)
                    //  binding.progressBar.hide()
                    //result.data?.let { bindView(binding, it) }
                }
                Result.Status.LOADING -> {
                    // binding.progressBar.show()
                }
                Result.Status.ERROR -> {
                    Log.d("Albums ", "${result.message}")
                    // binding.progressBar.hide()
                    //  Snackbar.make(binding.coordinatorLayout, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setUpTabsAndViewPager(data: List<Album>?) {
        for (i in 0 until data!!.size) {
            tabs.addTab(tabs.newTab().setText(data[i].title))
        }
        val viewPagerAdapter = AlbumStateAdapter(supportFragmentManager, data)
        viewpager_main.adapter = viewPagerAdapter
        viewpager_main.offscreenPageLimit = 5
        viewpager_main.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
    }
}
