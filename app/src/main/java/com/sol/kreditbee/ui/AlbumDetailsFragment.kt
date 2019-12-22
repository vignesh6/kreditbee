package com.sol.kreditbee.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sol.kreditbee.R
import com.sol.kreditbee.data.Result
import com.sol.kreditbee.databinding.FragmentAlbumDetailBinding
import com.sol.kreditbee.di.Injectable
import com.sol.kreditbee.di.injectViewModel
import com.sol.kreditbee.ui.viewmodel.AlbumDetailsViewModel
import com.sol.kreditbee.util.ConnectivityUtil
import javax.inject.Inject

const val ALBUM_ID = "albumid"
const val SPAN_COUNT = 4

class AlbumDetailsFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AlbumDetailsViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var binding: FragmentAlbumDetailBinding
    private val gridDecoration: RecyclerView.ItemDecoration by lazy {
        GridSpacingItemDecoration(
            SPAN_COUNT, resources.getDimension(R.dimen.margin_grid).toInt()
        )
    }
    private val adapter: AlbumDetailsAdapter by lazy { AlbumDetailsAdapter() }
    private var albumId: Int? = null

    companion object {
        fun newInstance(albumId: Int): AlbumDetailsFragment {
            val albumDetailFragment = AlbumDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(ALBUM_ID, albumId)
            albumDetailFragment.arguments = bundle
            return albumDetailFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumId = arguments!!.getInt(ALBUM_ID)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.connectivityAvailable = ConnectivityUtil.isConnected(context!!)
        viewModel.albumId = albumId
        binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        gridLayoutManager = GridLayoutManager(activity, SPAN_COUNT)
        setLayoutManager()
        binding.recyclerViewAlbumDetail.adapter = adapter
        context ?: return binding.root
        getAlbumsInfo(adapter)
        return binding.root
    }

    private fun setLayoutManager() {
        val recyclerView = binding.recyclerViewAlbumDetail

        var scrollPosition = 0
        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }
        recyclerView.addItemDecoration(gridDecoration)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.scrollToPosition(scrollPosition)

    }

    private fun getAlbumsInfo(adapter: AlbumDetailsAdapter) {
        viewModel.getAlbumsDetailsById.observe(this, Observer {
                result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    Log.d("Albums ", "${Gson().toJson(result.data)}")
                    if(result.data!!.isNotEmpty())
                        viewModel.albumDetails.observe(viewLifecycleOwner) {
                             binding.progressBar.hide()
                            adapter.submitList(it)
                        }

                    //result.data?.let { bindView(binding, it) }
                }
                Result.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Result.Status.ERROR -> {
                    Log.d("Albums ", "${result.message}")
                     binding.progressBar.hide()
                    //  Snackbar.make(binding.coordinatorLayout, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }
    fun ProgressBar.hide() {
        visibility = View.GONE
    }

    fun ProgressBar.show() {
        visibility = View.VISIBLE
    }
}