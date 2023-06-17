package com.ayberk.filmyorum.ui.fragments.home.pages

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color.*
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.databinding.FragmentFavoriteBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarMenu
import com.google.android.material.navigation.NavigationBarMenuView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.type.Color
import dagger.hilt.android.AndroidEntryPoint


@Suppress("UNREACHABLE_CODE", "DEPRECATION")
@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth
   // private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.imageViewKullanici.setImageResource(R.drawable.person)
        auth = FirebaseAuth.getInstance()
        val user = Firebase.auth.currentUser
        binding.textViewemail.text = user?.email

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cikis_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){
            R.id.anasayfa -> {
                findNavController().navigate(R.id.homeFragment)
                Toast.makeText(requireContext(),"Anasayfa",Toast.LENGTH_SHORT).show()
            }
            R.id.cikis ->
                requestLogout()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun requestLogout() {
        val builder = AlertDialog.Builder(requireContext())


        builder.setMessage("Uygulamadan çıkış yapmak istediğinizden emin misiniz?")
            .setCancelable(true)
            .setPositiveButton("UYGULAMADAN ÇIKIŞ YAP") { dialog, id ->
                auth.signOut()
                findNavController().navigate(R.id.action_favoriteFragment_to_mainActivity)

            }
            .setNegativeButton("UYGULAMAYA GERİ DÖN") { dialog, id ->
                dialog.cancel()
            }
        val alert: AlertDialog = builder.create()
        alert.show()
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(RED)
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(WHITE)
    }


    @SuppressLint("ResourceType", "SetTextI18n", "SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {


            val avatar = FavoriteFragmentArgs.fromBundle(it).avatar

            if (avatar == "İzleyici") {

                binding.txtNick.text = avatar
                binding.imageViewKullanici.setImageResource(R.drawable.woman)

            }
            if ( avatar == "Dinleyici") {

                binding.txtNick.text = avatar
                binding.imageViewKullanici.setImageResource(R.drawable.womannn2)

            }
            if ( avatar == "Yorumlayıcı") {

                binding.txtNick.text = avatar
                binding.imageViewKullanici.setImageResource(R.drawable.user2)

            }
            if  (avatar == "Filmkolik") {

                binding.txtNick.text = avatar
                binding.imageViewKullanici.setImageResource(R.drawable.woman2)

            }
            if ( avatar == "Film Delisi") {

                binding.txtNick.text = avatar
                binding.imageViewKullanici.setImageResource(R.drawable.userrr)

            }
            if ( avatar == "Film Canavarı") {

                binding.txtNick.text = avatar
                binding.imageViewKullanici.setImageResource(R.drawable.gamer)

            }
            if (avatar == "Film Muhtarı") {

                binding.txtNick.text = avatar
                binding.imageViewKullanici.setImageResource(R.drawable.man)

            }
            if ( avatar == "Çılgın izleyici") {
                binding.txtNick.text = avatar
                binding.imageViewKullanici.setImageResource(R.drawable.mann)

            }

        }

      //  val botnav = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
         //   botnav.visibility = View.GONE
    }

    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }