package com.ayberk.filmyorum.adapter

import android.annotation.SuppressLint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.di.dao.FavoriteDao
import com.ayberk.filmyorum.di.dao.FavoriteDatabase
import com.ayberk.filmyorum.di.dao.GenreData
import com.ayberk.filmyorum.di.retrofit.models.FavoriteDataClass
import com.ayberk.filmyorum.di.retrofit.models.Result
import com.ayberk.filmyorum.ui.fragments.home.pages.HomeFragmentDirections
import com.bumptech.glide.Glide
import java.util.Locale


class RecentMovieAdapter(private val isFirstScreen : Boolean = true): RecyclerView.Adapter<RecentMovieAdapter.MyCustomHolder>() {

    private lateinit var db : FavoriteDatabase
    private lateinit var dao : FavoriteDao
    var liveData : List<Result>? = null
    var genreList : List<GenreData>? = null


        override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): RecentMovieAdapter.MyCustomHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_movie_item,parent,false)
        return MyCustomHolder(view)
    }

    override fun onBindViewHolder(holder: RecentMovieAdapter.MyCustomHolder,position: Int) {

        holder.bind(liveData!!.get(position),genreList!!)
        holder.btnyorum.findViewById<Button>(R.id.btnYorum)

        db = Room.databaseBuilder(holder.itemView.context.applicationContext,
            FavoriteDatabase::class.java,"FavoriteMovie")
            .allowMainThreadQueries()
            .build()
        dao = db.favoriteDao()

        val dataExists = dao.checkIfDataExists(liveData!![position].id.toInt()) > 0

        if (dataExists == true) {
            // veritabanında veri varsa, image görüntülenecek
            holder.imgFavoriteYes.visibility = ViewGroup.VISIBLE
            holder.imgFav.visibility = ViewGroup.GONE
        } else {
            // veritabanında veri yoksa, image gizlenecek
            holder.imgFavoriteYes.visibility = ViewGroup.GONE
            holder.imgFav.visibility = ViewGroup.VISIBLE
        }


        holder.imgFav.setOnClickListener {

            val data = FavoriteDataClass(
                liveData!!.get(position).title,
                liveData!!.get(position).vote_average,
                liveData!!.get(position).poster_path,
                liveData!!.get(position).release_date,
                liveData!!.get(position).id,
            )
            dao.insert(data)
            notifyDataSetChanged()
            Toast.makeText(holder.itemView.context, "Favorilere Eklendi", Toast.LENGTH_SHORT).show()

        }

    }


    override fun getItemCount(): Int {

        if(liveData == null){
            return 0
        }
        else if(isFirstScreen)
            return 20
        else{
            return  liveData!!.size
        }
    }

    class MyCustomHolder(val view: View):
        RecyclerView.ViewHolder(view){

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtGenre = view.findViewById<TextView>(R.id.txtGenre)
        val posterView = view.findViewById<ImageView>(R.id.posterView )
        val imgFav = view.findViewById<ImageView>(R.id.imgFavorite )
        val imgFavoriteYes = view.findViewById<ImageView>(R.id.imgFavoriYes )
        val txtReleaseDate = view.findViewById<TextView>(R.id.txtReleaseDate)
        val txtVoteAverage = view.findViewById<TextView>(R.id.txtYorumAvarage)
        val txtPuanlama = view.findViewById<TextView>(R.id.txtPuanlama)
        val buttonoy = view.findViewById<Button>(R.id.buttonoy)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar3)
        val btnyorum = view.findViewById<Button>(R.id.btnYorum)



        @SuppressLint("SetTextI18n")
        fun bind(data: Result,genreList:List<GenreData>){
            txtTitle.text = data.title
            txtGenre.text ="Deneme,Deneme,Deneme"

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

            txtReleaseDate.text = data.release_date
            txtVoteAverage.text = data.vote_average.toString() + " / 10"
            Glide.with(posterView)
                .load("https://image.tmdb.org/t/p/w342/" + data.poster_path)
                .into(posterView)

            buttonoy.setOnClickListener {
                val puan = ratingBar.rating.toString()
                val oylama = buttonoy.findViewById(R.id.buttonoy) as Button
                val rating = ratingBar.findViewById(R.id.ratingBar3) as RatingBar

                txtPuanlama.text = puan + " / 5.0  (Oy kullanılmıştır)"

                buttonoy.isClickable = false
                oylama.visibility = View.INVISIBLE
                rating.visibility = View.INVISIBLE


            }
            val btnyorum =  view.findViewById<Button>(R.id.btnYorum)
            btnyorum.setOnClickListener {
                view.findNavController().navigate(R.id.action_homeFragment_to_yorumFragment)
            }
        }
    }

    fun setLists(liveData: List<Result>,genreList: List<GenreData>){
        this.liveData=liveData
        this.genreList=genreList
        notifyDataSetChanged()

    }
}


