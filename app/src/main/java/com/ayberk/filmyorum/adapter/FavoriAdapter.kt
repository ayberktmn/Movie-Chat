package com.ayberk.filmyorum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.databinding.FavoriteItemBinding
import com.ayberk.filmyorum.di.dao.FavoriteDao
import com.ayberk.filmyorum.di.dao.FavoriteDatabase
import com.ayberk.filmyorum.di.retrofit.models.FavoriteDataClass
import com.bumptech.glide.Glide

class FavoriAdapter(val movieList : List<FavoriteDataClass>):RecyclerView.Adapter<FavoriAdapter.MovieHolder>() {

    private lateinit var db : FavoriteDatabase
    private lateinit var adventDao: FavoriteDao

    class MovieHolder(val binding:FavoriteItemBinding) :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
       val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.binding.txtTitle.text = movieList[position].title
        holder.binding.txtReleaseDate.text = movieList[position].release_date
        holder.binding.txtYorumAvarage.text = movieList[position].vote_average.toString()
        Glide.with(holder.binding.posterView)
            .load("https://image.tmdb.org/t/p/w342/" + movieList[position].poster_path)
            .into(holder.binding.posterView)

        db =
            Room.databaseBuilder(holder.binding.root.context, FavoriteDatabase::class.java, "FavoriteMovie")
                .allowMainThreadQueries()
                .build()
        adventDao = db.favoriteDao()

        holder.binding.imgDelete.setOnClickListener {

            val favoriteMovie = movieList[position]
            adventDao.delete(favoriteMovie)

            Toast.makeText(holder.itemView.context, "Favorilerden Silindi", Toast.LENGTH_SHORT)
                .show()

            // Favori müzik öğesini listenizden kaldırın ve RecyclerView'i güncelleyin
            notifyItemRemoved(position)
            holder.itemView.findNavController().navigate(R.id.favoriFragment)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}