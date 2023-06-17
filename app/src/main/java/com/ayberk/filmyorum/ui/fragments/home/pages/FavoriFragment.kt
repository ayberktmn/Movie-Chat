package com.ayberk.filmyorum.ui.fragments.home.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ayberk.filmyorum.adapter.FavoriAdapter
import com.ayberk.filmyorum.databinding.FragmentFavoriBinding
import com.ayberk.filmyorum.di.dao.FavoriteDao
import com.ayberk.filmyorum.di.dao.FavoriteDatabase
import com.ayberk.filmyorum.di.retrofit.models.FavoriteDataClass

class FavoriFragment : Fragment() {

    private lateinit var db : FavoriteDatabase
    private lateinit var dao : FavoriteDao

    private var _binding: FragmentFavoriBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Room.databaseBuilder(requireContext().applicationContext, FavoriteDatabase::class.java, "FavoriteMovie")
            .allowMainThreadQueries()
            .build()
        dao = db.favoriteDao()

        val recyclerViewAdapter = FavoriAdapter(dao.getAll() as ArrayList<FavoriteDataClass>)
        binding.rcylerFavori.adapter = recyclerViewAdapter

        binding.rcylerFavori.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}