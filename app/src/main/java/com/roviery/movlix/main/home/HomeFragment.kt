package com.roviery.movlix.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.roviery.core.data.source.Resource
import com.roviery.core.ui.MovieAdapter
import com.roviery.core.ui.TvShowAdapter
import com.roviery.movlix.databinding.FragmentHomeBinding
import com.roviery.movlix.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var tvShowAdapter: TvShowAdapter
    private var movieShimmerFrameLayout: ShimmerFrameLayout? = null
    private var tvShowShimmerFrameLayout: ShimmerFrameLayout? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            movieShimmerFrameLayout = binding?.homeShimmerRvMovie
            tvShowShimmerFrameLayout = binding?.homeShimmerRvTvshow

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
        homeViewModel.popularMovie.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        binding?.homeTvError?.visibility = View.GONE
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding?.homeTvError?.visibility = View.GONE
                        binding?.progressBar?.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                        movieShimmerFrameLayout?.stopShimmer()
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.homeTvError?.visibility = View.VISIBLE
                    }
                }
            }
        }

        with(binding?.homeRvPopularMovie) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
        }
    }

    private fun loadTvShow() {
        homeViewModel.popularTvShow.observe(viewLifecycleOwner) { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> {
                        binding?.homeTvError?.visibility = View.GONE
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding?.homeTvError?.visibility = View.GONE
                        binding?.progressBar?.visibility = View.GONE
                        tvShowAdapter.setData(tvShow.data)
                        tvShowShimmerFrameLayout?.stopShimmer()
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.homeTvError?.visibility = View.VISIBLE
                    }
                }
            }
        }

        with(binding?.homeRvPopularTvshow) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = tvShowAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.homeRvPopularMovie?.adapter = null
        binding?.homeRvPopularTvshow?.adapter = null
        _binding = null
    }
}