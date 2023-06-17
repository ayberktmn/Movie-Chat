package com.ayberk.filmyorum.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayberk.filmyorum.di.retrofit.RetrofitRepository
import com.ayberk.filmyorum.di.retrofit.models.Genre
import com.ayberk.filmyorum.di.retrofit.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel  @Inject constructor(private val repository: RetrofitRepository) : ViewModel() {

    var popularMovieList: MutableLiveData<Movie>
    var recentMovieList: MutableLiveData<Movie>
    var genreList: MutableLiveData<Genre>

    init {
        popularMovieList = MutableLiveData()
        recentMovieList = MutableLiveData()
        genreList = MutableLiveData()
    }

    fun getObserverLiveData(isPopular : Boolean):MutableLiveData<Movie> {
        if(isPopular)
            return popularMovieList
          else
            return recentMovieList
    }

    fun getObserverGenre():MutableLiveData<Genre> {

        return genreList
    }

    fun  loadGenreData(){
      repository.getAllGenres(genreList)
    }

    fun  loadData(page:String,isPopular: Boolean){
        if(isPopular)
            repository.getPopularMovies(page,popularMovieList)
          else
            repository.getRecentMovies(page,recentMovieList)
    }


}