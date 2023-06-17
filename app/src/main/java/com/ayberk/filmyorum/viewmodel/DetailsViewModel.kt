package com.ayberk.filmyorum.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.filmyorum.di.retrofit.YorumRetRepo
import com.ayberk.filmyorum.di.retrofit.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repo: YorumRetRepo) : ViewModel()  {

    var popularMovieList: MutableLiveData<Movie>

    init {
        popularMovieList = MutableLiveData()

    }

    fun getDetailsLiveData(): MutableLiveData<Movie> {
        return popularMovieList
    }

    fun  loadPopularData(page:String){

        repo.getPopularMovies(page,popularMovieList)

    }
    fun  loadData(page:String){

        repo.getPopularMovies(page,popularMovieList)


    }
}