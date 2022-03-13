package com.roviery.movlix.main.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.roviery.core.data.source.Resource
import com.roviery.core.ui.SearchMovieAdapter
import com.roviery.core.ui.SearchTvShowAdapter
import com.roviery.movlix.R
import com.roviery.movlix.databinding.FragmentSearchBinding
import com.roviery.movlix.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var searchMovieAdapter: SearchMovieAdapter
    private lateinit var searchTvShowAdapter: SearchTvShowAdapter
    private lateinit var option: String

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setSpinner()

            searchMovieAdapter = SearchMovieAdapter()
            searchTvShowAdapter = SearchTvShowAdapter()

            searchMovieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }

            searchTvShowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_TVSHOW, selectedData)
                startActivity(intent)
            }

            binding.etQuery.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (option == "Movie") {
                        searchMovie(binding.etQuery.text.toString())
                    } else {
                        searchTvShow(binding.etQuery.text.toString())
                    }
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }

        }
    }

    private fun searchMovie(q: String) {
        Log.d("SearchFragment Query", q)
        searchViewModel.searchMovie(q).observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        binding.searchTvError.visibility = View.GONE
                        binding.searchProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.searchTvError.visibility = View.GONE
                        binding.searchProgressBar.visibility = View.GONE
                        searchMovieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.searchProgressBar.visibility = View.GONE
                        binding.searchTvError.visibility = View.VISIBLE
                    }
                }
            }

        }

        with(binding.searchRv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = searchMovieAdapter
        }
    }

    private fun searchTvShow(q: String){
        Log.d("SearchFragment Query", q)
        searchViewModel.searchTvShow(q).observe(viewLifecycleOwner) { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> {
                        binding.searchTvError.visibility = View.GONE
                        binding.searchProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.searchTvError.visibility = View.GONE
                        binding.searchProgressBar.visibility = View.GONE
                        searchTvShowAdapter.setData(tvShow.data)
                    }
                    is Resource.Error -> {
                        binding.searchProgressBar.visibility = View.GONE
                        binding.searchTvError.visibility = View.VISIBLE
                    }
                }
            }

        }

        with(binding.searchRv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = searchTvShowAdapter
        }

    }

    private fun setSpinner() {
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.option,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.searchOption.adapter = adapter
        binding.searchOption.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        option = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}