package com.roviery.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.ui.MovieAdapter
import com.roviery.core.ui.TvShowAdapter
import com.roviery.favorite.databinding.FragmentFavoriteBinding
import com.roviery.favorite.di.favoriteModule
import com.roviery.movlix.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var tvShowAdapter: TvShowAdapter

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        loadKoinModules(favoriteModule)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            movieAdapter = MovieAdapter()
            tvShowAdapter = TvShowAdapter()

            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }

            tvShowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_TVSHOW, selectedData)
                startActivity(intent)
            }

            loadMovie()
            loadTvShow()
        }
    }

    private fun loadMovie() {
        favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner) { movie ->
            Log.d("Favorite Movie", movie.toString())
            movieAdapter.setData(movie)
        }

        with(binding?.favoriteRvFavoriteMovie) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
        }
    }

    private fun loadTvShow() {
        favoriteViewModel.favoriteTvShow.observe(viewLifecycleOwner) { tvShow ->
            tvShowAdapter.setData(tvShow)
        }

        with(binding?.favoriteRvFavoriteTvshow) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = tvShowAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.favoriteRvFavoriteMovie?.adapter = null
        binding?.favoriteRvFavoriteTvshow?.adapter = null
        _binding = null
    }
}