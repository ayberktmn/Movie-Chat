package com.ayberk.filmyorum.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.databinding.FragmentMainBinding
import com.ayberk.filmyorum.di.retrofit.models.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        if (sessionManager.getIsFirstRun())
            sessionManager.setIsFirstRun(false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.bottomNavigationView.setOnNavigationItemReselectedListener {}
        binding.bottomNavigationView.setOnItemSelectedListener {

                when (it.itemId) {
                    R.id.home ->
                        childFragmentManager.primaryNavigationFragment?.findNavController()
                            ?.navigate(R.id.homeFragment)

                    R.id.kullanici ->
                        childFragmentManager.primaryNavigationFragment?.findNavController()
                            ?.navigate(R.id.favoriteFragment)

                    R.id.settings -> childFragmentManager.primaryNavigationFragment?.findNavController()
                        ?.navigate(R.id.settingsFragment)

                    R.id.yorum -> childFragmentManager.primaryNavigationFragment?.findNavController()
                        ?.navigate(R.id.yorumFragment)

                    R.id.favori -> childFragmentManager.primaryNavigationFragment?.findNavController()
                        ?.navigate(R.id.favoriFragment)

                    else -> {

                    }
                }
            true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

