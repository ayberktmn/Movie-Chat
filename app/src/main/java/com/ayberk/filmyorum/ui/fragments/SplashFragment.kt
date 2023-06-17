package com.ayberk.filmyorum.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.databinding.FragmentSplashBinding
import com.ayberk.filmyorum.di.retrofit.models.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding : FragmentSplashBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)
        val view = binding.root

        Handler(Looper.getMainLooper()).postDelayed({

                                                    if (sessionManager.getIsFirstRun()){
                                                        findNavController().navigate(R.id.action_splashFragment_to_appIntroFragment)
                                                    }
                                                    else{
                                                        findNavController().navigate(R.id.loginFragment)
                                                        // yorum sayfası findNavController().navigate(R.id.action_splashFragment_to_girisFragment)
                                                    }
        },6500)

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}