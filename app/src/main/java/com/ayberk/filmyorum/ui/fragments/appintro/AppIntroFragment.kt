package com.ayberk.filmyorum.ui.fragments.appintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ayberk.filmyorum.adapter.ViewPagerAdapter
import com.ayberk.filmyorum.databinding.FragmentAppintroBinding
import com.ayberk.filmyorum.ui.fragments.appintro.pages.FirstScreen
import com.ayberk.filmyorum.ui.fragments.appintro.pages.SecondScreen
import com.ayberk.filmyorum.ui.fragments.appintro.pages.ThirdScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppIntroFragment : Fragment() {

    private var _binding : FragmentAppintroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppintroBinding.inflate(inflater,container,false)
        val view = binding.root

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)

        binding.viewPager.adapter = adapter

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}