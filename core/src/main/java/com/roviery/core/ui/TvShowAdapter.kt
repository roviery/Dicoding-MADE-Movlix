package com.roviery.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.roviery.core.R
import com.roviery.core.databinding.ItemListBinding
import com.roviery.core.domain.model.TvShow
import com.roviery.core.utils.Credentials

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listData = ArrayList<TvShow>()
    var onItemClick: ((TvShow) -> Unit)? = null

    fun setData(newListData: List<TvShow>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TvShowViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: TvShow) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Credentials.POSTER_BASE_URL + data.tvShowPoster)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(cardImg)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }
}