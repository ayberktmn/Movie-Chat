package com.ayberk.filmyorum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.di.dao.GenreData
import com.ayberk.filmyorum.di.retrofit.models.Result
import com.bumptech.glide.Glide
import java.util.*

class YorumPopulerAdapter: RecyclerView.Adapter<YorumPopulerAdapter.MyCustomHolder>() {

    var liveData : List<Result>? = null
    var genreList : List<GenreData>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): YorumPopulerAdapter.MyCustomHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.yorumpopuler,parent,false)
        return MyCustomHolder(view,genreList!!)
    }

    override fun onBindViewHolder(holder: YorumPopulerAdapter.MyCustomHolder, position: Int) {

        holder.bind(liveData!!.get(position),genreList!!)

    }

    override fun getItemCount(): Int {

        if(liveData == null){
            return 0
        }
        else{
            return 3
        }
    }

    class MyCustomHolder(val view: View,genreList : List<GenreData>):
        RecyclerView.ViewHolder(view){

        val txttitlepop = view.findViewById<TextView>(R.id.titlepop)
      //  val txtPopGenre = view.findViewById<TextView>(R.id.txtPopGenre)
        val PopposterView = view.findViewById<ImageView>(R.id.PopposterView )
        val yorumavarage = view.findViewById<TextView>(R.id.txtYorumAvarage)

        fun bind(data: Result, genreList: List<GenreData>){
            txttitlepop.text = data.title
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
            yorumavarage.text = data.vote_average.toString() + " / 10"
            genres = genres.substring(0,genres.length - 2)
         //   txtPopGenre.text = genres
            var into = Glide.with(PopposterView)
                .load("https://image.tmdb.org/t/p/w342/" + data.poster_path)
                .into(PopposterView)
        }

        }

    fun setLists(liveData: List<Result>,genreList: List<GenreData>){
        this.liveData=liveData
        this.genreList = genreList
        notifyDataSetChanged()
    }
}