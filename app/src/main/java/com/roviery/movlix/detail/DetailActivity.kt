package com.roviery.movlix.detail

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.roviery.core.domain.model.Movie
import com.roviery.core.domain.model.TvShow
import com.roviery.core.utils.Credentials
import com.roviery.movlix.R
import com.roviery.movlix.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFullScreen(window)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        val detailTvShow = intent.getParcelableExtra<TvShow>(EXTRA_TVSHOW)

        if (detailMovie != null) {
            showDetailMovie(detailMovie)
        } else {
            showDetailTvShow(detailTvShow)
        }

        binding.detailCloseBtn.setOnClickListener {
            finish()
            onBackPressed()
        }
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            binding.detailPb.visibility = View.VISIBLE
            supportActionBar?.title = detailMovie.movieTitle
            Glide.with(this@DetailActivity)
                .load(Credentials.BACKDROP_BASE_URL + detailMovie.movieBackdrop)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.detailBackdrop)
            Glide.with(this@DetailActivity)
                .load(Credentials.Companion.POSTER_BASE_URL + detailMovie.moviePoster)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.detailPoster)
            binding.detailOverview.text = detailMovie.movieOverview
            binding.detailItemReleaseDate.text = detailMovie.movieReleaseDate
            binding.detailItemRating.text = detailMovie.movieVote.toString()

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.detailFavBtn.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoritePopularMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
            binding.detailPb.visibility = View.GONE
        }
    }

    private fun showDetailTvShow(detailTvShow: TvShow?) {
        detailTvShow?.let {
            binding.detailPb.visibility = View.VISIBLE
            supportActionBar?.title = detailTvShow.tvShowTitle
            Glide.with(this@DetailActivity)
                .load(Credentials.BACKDROP_BASE_URL + detailTvShow.tvShowBackdrop)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.detailBackdrop)
            Glide.with(this@DetailActivity)
                .load(Credentials.POSTER_BASE_URL + detailTvShow.tvShowPoster)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.detailPoster)
            binding.detailOverview.text = detailTvShow.tvShowOverview
            binding.detailItemReleaseDate.text = detailTvShow.tvShowReleaseDate
            binding.detailItemRating.text = detailTvShow.tvShowVote.toString()

            var statusFavorite = detailTvShow.isFavorite
            setStatusFavorite(statusFavorite)
            binding.detailFavBtn.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoritePopularTvShow(detailTvShow, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
            binding.detailPb.visibility = View.GONE
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.detailFavBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_solid))
        } else {
            binding.detailFavBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

    private fun setFullScreen(window: Window){
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVSHOW = "extra_tvshow"
    }
}