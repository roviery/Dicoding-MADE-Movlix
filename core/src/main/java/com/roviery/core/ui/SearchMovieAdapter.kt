package com.roviery.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.roviery.core.R
import com.roviery.core.databinding.SearchListBinding
import com.roviery.core.domain.model.Movie
import com.roviery.core.utils.Credentials

class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.SearchViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.search_list, parent, false)
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = SearchListBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Credentials.POSTER_BASE_URL + data.moviePoster)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemPoster)
                itemTitle.text = data.movieTitle
                itemReleaseDate.text = data.movieReleaseDate
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }


}