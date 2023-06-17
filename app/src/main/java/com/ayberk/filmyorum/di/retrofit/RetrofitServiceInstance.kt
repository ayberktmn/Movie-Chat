package com.ayberk.filmyorum.di.retrofit

import com.ayberk.filmyorum.di.retrofit.models.Genre
import com.ayberk.filmyorum.di.retrofit.models.Movie
import com.ayberk.filmyorum.di.retrofit.models.Trailer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInstance {

    @GET("3/movie/popular?api_key=802b2c4b88ea1183e50e6b285a27696e")
    fun getPopularVideos(@Query("page")query: String): retrofit2.Call<Movie>

    @GET("3/movie/now_playing?api_key=802b2c4b88ea1183e50e6b285a27696e")
    fun getRecentVideos(@Query("page")query: String): retrofit2.Call<Movie>

    @GET("3/genre/movie/list?api_key=802b2c4b88ea1183e50e6b285a27696e")
    fun getGenres(): retrofit2.Call<Genre>

    @GET("3/movie/{id}/videos?api_key=802b2c4b88ea1183e50e6b285a27696e")
    fun getTrailerTeasers(@Path("id")id: Int): retrofit2.Call<Trailer>

    @GET("3/search/movie?api_key=802b2c4b88ea1183e50e6b285a27696e&language=en-US")
    fun getSuggestions(@Query("query")query: String): retrofit2.Call<Movie>

}
