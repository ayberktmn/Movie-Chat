package com.ayberk.filmyorum.ui.fragments.home.pages


import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.databinding.FragmentDetailsBinding
import com.ayberk.filmyorum.databinding.FragmentFavoriteBinding
import com.ayberk.filmyorum.di.retrofit.models.Movie
import com.ayberk.filmyorum.viewmodel.DetailsViewModel
import com.ayberk.filmyorum.viewmodel.YorumPageViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewDetailsModel: DetailsViewModel by viewModels()

    lateinit var resultList: com.ayberk.filmyorum.di.retrofit.models.Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchMovies()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDetailsModel.getDetailsLiveData().observe(viewLifecycleOwner, object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if(t != null ){
                    arguments?.let {
                        val movieID = DetailsFragmentArgs.fromBundle(it).movieId
                    resultList = t.results[movieID]
                    binding.txtFilmisim.text= resultList.title
                    binding.txtOverview.text = resultList.overview
                    binding.txtFilmName.text = resultList.title
                    binding.txtYildiz.text= resultList.vote_average.toString()
                    var into = Glide.with(binding.posterView)
                        .load("https://image.tmdb.org/t/p/w342/" + resultList.poster_path)
                        .into(binding.posterView)

                  }
                }
            }

        })
              //  println(index)

            //  binding.txtid.text = movieID.toString()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.backimg.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        return view
    }

    fun fetchMovies(){

        CoroutineScope(Dispatchers.IO).launch {

            val job1 : Deferred<Unit> = async {
                viewDetailsModel.loadPopularData("1")
            }

            job1.await()

        }
    }

}