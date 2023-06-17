package com.ayberk.filmyorum.ui.fragments.home.pages

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.adapter.MovieAdapter
import com.ayberk.filmyorum.adapter.RecentMovieAdapter
import com.ayberk.filmyorum.databinding.FragmentHomeBinding
import com.ayberk.filmyorum.di.dao.GenreData
import com.ayberk.filmyorum.di.retrofit.models.Movie
import com.ayberk.filmyorum.viewmodel.GenreViewModel
import com.ayberk.filmyorum.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter:MovieAdapter
    private lateinit var recentMovieAdapter: RecentMovieAdapter

    private var genreList : List<GenreData>? = null

    val viewModel by lazy {
        ViewModelProvider(this,defaultViewModelProviderFactory).get(HomePageViewModel::class.java)
    }
    val genreViewModel by lazy {
        ViewModelProvider(this,defaultViewModelProviderFactory).get(GenreViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        initRecyclerViews()

        viewModel.getObserverLiveData(true).observe(viewLifecycleOwner, object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if(t != null){

                    movieAdapter.setLists(t.results,genreList!!)

                }

            }

        })

        viewModel.getObserverLiveData(false).observe(viewLifecycleOwner, object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if(t != null){
                    recentMovieAdapter.setLists(t.results,genreList!!)

                }
            }
        })

        genreViewModel.getRecordsObserver().observe(viewLifecycleOwner,object : Observer<List<GenreData>>{
            override fun onChanged(t: List<GenreData>?) {
                if (t != null){
                    genreList = t
                    fetchMovies()
                }
            }

        })


        fetchMovies()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecyclerViews(){

        val lmHorizontal = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val lmVertical = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        val recyclerView = binding.popularRecyclerview
        val recentRecyclerView = binding.recentrecyclerview

        recentRecyclerView.layoutManager =lmVertical
        recyclerView.layoutManager=lmHorizontal
        movieAdapter = MovieAdapter()
        recentMovieAdapter= RecentMovieAdapter()
        recyclerView.adapter=movieAdapter
        recentRecyclerView.adapter=recentMovieAdapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun fetchMovies(){

        CoroutineScope(Dispatchers.IO).launch {

            val job1 : Deferred<Unit> = async {

                viewModel.loadData("1", true)
            }
            val job2 : Deferred<Unit> = async {
                viewModel.loadData("1", false)
            }


            job1.await()
            job2.await()
        }
    }
}