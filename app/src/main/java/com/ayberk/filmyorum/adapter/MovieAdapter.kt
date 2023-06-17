package com.ayberk.filmyorum.adapter


import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.di.dao.FavoriteDao
import com.ayberk.filmyorum.di.dao.FavoriteDatabase
import com.ayberk.filmyorum.di.dao.GenreData
import com.ayberk.filmyorum.di.retrofit.models.FavoriteDataClass
import com.bumptech.glide.Glide
import com.ayberk.filmyorum.di.retrofit.models.Result
import com.ayberk.filmyorum.ui.fragments.home.pages.DetailsFragmentArgs

import com.ayberk.filmyorum.ui.fragments.home.pages.HomeFragmentDirections
import com.ayberk.filmyorum.ui.fragments.home.pages.loginFragmentDirections
import java.util.*


class MovieAdapter(private val isFirstScreen : Boolean = true):RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {


    var liveData : List<Result>? = null
    var genreList : List<GenreData>? = null

    override fun onCreateViewHolder(parent: ViewGroup,viewType:Int): MovieAdapter.MyCustomHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.populer_movie_item,parent,false)
        return MyCustomHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyCustomHolder,position: Int) {

        holder.bind(liveData!!.get(position),genreList!!)
        holder.posterView?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(position)
            holder.posterView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {

        if(liveData == null){
            return 0
        }
        else if(isFirstScreen)
            return 5
        else{
            return  liveData!!.size

        }
    }

    class MyCustomHolder(val view:View):
        RecyclerView.ViewHolder(view){

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtGenre = view.findViewById<TextView>(R.id.txtGenre)
        val posterView = view.findViewById<ImageView>(R.id.posterView)



        fun bind(data: Result,genreList : List<GenreData>){
            txtTitle.text = data.title
            val lang = Locale.getDefault().language

            var genres = ""
            for (id in data.genre_ids) {

                var result = genreList.find { x -> x.genre_id == id }

                if (result != null){
                    if (lang == "tr") {
                        genres += result!!.tr_name + ","
                    }
                    else{
                        genres += result!!.en_name + ","
                    }
                }
            }
            genres = genres.substring(0,genres.length - 2)
            txtGenre.text = genres
            var into = Glide.with(posterView)
                .load("https://image.tmdb.org/t/p/w342/" + data.poster_path)
                .into(posterView)
        }
    }


    fun setLists(liveData: List<Result>, genreList: List<GenreData>){
        this.liveData=liveData
        this.genreList = genreList
        notifyDataSetChanged()
    }
}

