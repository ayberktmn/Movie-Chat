package com.ayberk.filmyorum.ui.fragments.appintro.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.databinding.FragmentThirdBinding
import com.ayberk.filmyorum.di.dao.GenreData
import com.ayberk.filmyorum.di.retrofit.models.Genre
import com.ayberk.filmyorum.util.StringHelper
import com.ayberk.filmyorum.viewmodel.GenreViewModel
import com.ayberk.filmyorum.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdScreen : Fragment() {

    private var _binding : FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private lateinit var genreViewModel: GenreViewModel
    private lateinit var homePageViewModel: HomePageViewModel
    private var stringHelper : StringHelper? = null
    private var genreList : MutableList<GenreData>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        val view = binding.root

        stringHelper = StringHelper()
        genreList = mutableListOf()
        genreViewModel = ViewModelProvider(this).get(GenreViewModel::class.java)
        homePageViewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)
        homePageViewModel.getObserverGenre().observe(viewLifecycleOwner,{
            if (it != null){
              for (item in it.genres){
                    val tr_name = stringHelper!!.getTrName(item.name)
                    val genre = GenreData(0,item.id,item.name,tr_name)
                    genreList!!.add(genre)
              }
                genreViewModel.addAllGenres(genreList!!)
                findNavController().navigate(R.id.action_appIntroFragment_to_mainFragment)
            }
        })

        binding.imageButton.setOnClickListener {
            homePageViewModel.loadGenreData()
        }

        return view
    }




    override fun onResume() {
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val prevButton = activity?.findViewById<RelativeLayout>(R.id.prevButton)
        val nextButton = activity?.findViewById<RelativeLayout>(R.id.nxtButton)

        nextButton?.alpha = 0f
        nextButton?.isClickable =false

        prevButton?.setOnClickListener {
            viewPager?.currentItem = 1
        }

        nextButton?.setOnClickListener {
            viewPager?.currentItem = 3
        }

        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}