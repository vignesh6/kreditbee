package com.sol.kreditbee.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetail
import com.sol.kreditbee.databinding.ItemAlbumDetailsBinding

class AlbumDetailsAdapter() : PagedListAdapter<AlbumDetail,AlbumDetailsHolder>(AlbumDetailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailsHolder {
        return AlbumDetailsHolder(ItemAlbumDetailsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AlbumDetailsHolder, position: Int) {
        val albumDetail = getItem(position)
        albumDetail?.let {
            holder.apply {
                bind(createOnClickListener(albumDetail.thumbnailUrl), albumDetail)
                itemView.tag = albumDetail
            }
        }
    }
    private fun createOnClickListener(id: String): View.OnClickListener {
        return View.OnClickListener {

        }
    }

}
class AlbumDetailsHolder(private val binding: ItemAlbumDetailsBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(listener: View.OnClickListener, item: AlbumDetail) {
        binding.apply {
             clickListener = listener
            albumDetail = item
            executePendingBindings()
        }
    }
}
private class AlbumDetailDiffCallback : DiffUtil.ItemCallback<AlbumDetail>() {

    override fun areItemsTheSame(oldItem: AlbumDetail, newItem: AlbumDetail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumDetail, newItem: AlbumDetail): Boolean {
        return oldItem == newItem
    }
}

interface AlbAlbumDetailsListner{
    fun onAlbumClicked()
}
