package com.ayberk.filmyorum.ui.fragments.home.pages

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.filmyorum.adapter.MovieAdapter
import com.ayberk.filmyorum.adapter.RecentMovieAdapter
import com.ayberk.filmyorum.adapter.YorumPopulerAdapter
import com.ayberk.filmyorum.databinding.FragmentYorumBinding
import com.ayberk.filmyorum.di.dao.GenreData
import com.ayberk.filmyorum.di.retrofit.models.Movie
import com.ayberk.filmyorum.viewmodel.GenreViewModel
import com.ayberk.filmyorum.viewmodel.HomePageViewModel
import com.ayberk.filmyorum.viewmodel.YorumPageViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class YorumFragment : Fragment() {

    private var _binding: FragmentYorumBinding? = null
    private val binding get() = _binding!!
    private lateinit var firestore: FirebaseFirestore
    private lateinit var popularAdapter: YorumPopulerAdapter

    private var genreList : List<GenreData>? = null

    val viewModel by lazy {
        ViewModelProvider(this,defaultViewModelProviderFactory).get(YorumPageViewModel::class.java)
    }
    val genreViewModel by lazy {
        ViewModelProvider(this,defaultViewModelProviderFactory).get(GenreViewModel::class.java)

    }


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYorumBinding.inflate(inflater, container, false)
        val view = binding.root
        firestore = Firebase.firestore

        binding.rcyViewHaftaninFilm.adapter  = YorumPopulerAdapter()
        popularAdapter= YorumPopulerAdapter()
        initRecyclerViews()

        viewModel.getObserverLiveData().observe(viewLifecycleOwner, object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if(t != null){

                    popularAdapter.setLists(t.results, genreList!!)

                }

            }

        })

        genreViewModel.getRecordsObserver().observe(viewLifecycleOwner,object : Observer<List<GenreData>>{
            override fun onChanged(t: List<GenreData>?) {
                if (t != null){
                    genreList = t
                    fetchMovies()
                }
            }

        })
        viewModel.loadPopularData("1")

        binding.btnaksiyon.setOnClickListener {
            val action = YorumFragmentDirections.actionYorumFragmentToYorumodaFragment("Aksiyon")
            findNavController().navigate(action)

        }
        binding.btnanimasyon.setOnClickListener {
            val action = YorumFragmentDirections.actionYorumFragmentToYorumodaFragment("Animasyon")
            findNavController().navigate(action)

        }
        binding.btnbelgesel.setOnClickListener {
            val action = YorumFragmentDirections.actionYorumFragmentToYorumodaFragment("Belgesel")
            findNavController().navigate(action)

        }
        binding.btndrama.setOnClickListener {
            val action = YorumFragmentDirections.actionYorumFragmentToYorumodaFragment("Drama")
            findNavController().navigate(action)

        }
        binding.btnromantik.setOnClickListener {
            val action = YorumFragmentDirections.actionYorumFragmentToYorumodaFragment("Romantik")
            findNavController().navigate(action)

        }
        binding.btnbilim.setOnClickListener {
            val action = YorumFragmentDirections.actionYorumFragmentToYorumodaFragment("Bilim Kurgu")
            findNavController().navigate(action)

        }
        binding.btnfantezi.setOnClickListener {
            val action = YorumFragmentDirections.actionYorumFragmentToYorumodaFragment("Fantezi")
            findNavController().navigate(action)

        }
        binding.btnkomedi.setOnClickListener {
            val action = YorumFragmentDirections.actionYorumFragmentToYorumodaFragment("Komedi")
            findNavController().navigate(action)

        }
        binding.btnkorku.setOnClickListener {
            val action = YorumFragmentDirections.actionYorumFragmentToYorumodaFragment("Korku")
            findNavController().navigate(action)

        }
        return view
    }
    fun initRecyclerViews(){

        val lmHorizontal = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        val recyclerView = binding.rcyViewHaftaninFilm

        recyclerView.layoutManager=lmHorizontal
        popularAdapter = YorumPopulerAdapter()
        recyclerView.adapter=popularAdapter


    }
    fun fetchMovies(){

        CoroutineScope(Dispatchers.IO).launch {

            val job1 : Deferred<Unit> = async {
                viewModel.loadData("1")
            }
            val job2 : Deferred<Unit> = async {
                viewModel.loadData("1")
            }

            job1.await()
            job2.await()
        }
    }

}