package com.sol.kreditbee.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sol.kreditbee.data.repository.local.Album

class AlbumStateAdapter(fm:FragmentManager,private  val albumList:List<Album>): FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var albumFragments = HashMap<String, Fragment>()
    override fun getItem(position: Int): Fragment {
        val album = albumList[position]
        return when {
            albumFragments.containsKey(album.title) -> albumFragments[album.title]!!
            else -> {
                val fragment = AlbumDetailsFragment.newInstance(album.id)
               // fragment.setListener(listener)
                albumFragments[album.title] = fragment
                fragment
            }
        }
    }

    override fun getCount(): Int {
       return albumList.size
    }
}