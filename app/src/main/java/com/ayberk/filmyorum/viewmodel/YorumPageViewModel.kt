package com.ayberk.filmyorum.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.filmyorum.di.retrofit.YorumRetRepo
import com.ayberk.filmyorum.di.retrofit.models.Genre
import com.ayberk.filmyorum.di.retrofit.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YorumPageViewModel @Inject constructor(private val repo: YorumRetRepo) : ViewModel() {

    var popularMovieList: MutableLiveData<Movie>
    var genreList: MutableLiveData<Genre>


    init {
        popularMovieList = MutableLiveData()
        genreList = MutableLiveData()

    }

    fun getObserverLiveData(): MutableLiveData<Movie> {
            return popularMovieList
    }

    fun getObserverGenre():MutableLiveData<Genre> {

        return genreList
    }

    fun  loadPopularData(page:String){

            repo.getPopularMovies(page,popularMovieList)

    }
    fun  loadData(page:String){

            repo.getPopularMovies(page,popularMovieList)


    }
}