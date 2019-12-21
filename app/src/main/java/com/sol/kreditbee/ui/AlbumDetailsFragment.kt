package com.sol.kreditbee.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sol.kreditbee.R
import com.sol.kreditbee.di.Injectable
import com.sol.kreditbee.di.injectViewModel
import com.sol.kreditbee.ui.viewmodel.AlbumDetailsViewModel
import javax.inject.Inject

const val ALBUM_ID = "albumid"
class AlbumDetailsFragment:Fragment(),Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel:AlbumDetailsViewModel
    private var albumId:Int?= null
    companion object{
        fun  newInstance(albumId:Int):AlbumDetailsFragment{
            val albumDetailFragment = AlbumDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(ALBUM_ID,albumId)
            albumDetailFragment.arguments = bundle
            return albumDetailFragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        val view = inflater!!.inflate(R.layout.fragment_album_detail,container,false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumId = arguments!!.getInt(ALBUM_ID)!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }
}