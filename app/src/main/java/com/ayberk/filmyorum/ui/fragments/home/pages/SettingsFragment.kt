package com.ayberk.filmyorum.ui.fragments.home.pages

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.databinding.FragmentSettingsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    @SuppressLint("SuspiciousIndentation", "ResourceType", "UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.imageViewWomen1.setOnClickListener {
            binding.imageViewGoster.setImageResource(R.drawable.woman)
            binding.txtAvatarNick.text = "İzleyici"
        }
        binding.imageViewWomen2.setOnClickListener {
            binding.imageViewGoster.setImageResource(R.drawable.womannn2)
            binding.txtAvatarNick.text = "Dinleyici"
        }
        binding.imageViewWomen3.setOnClickListener {
            binding.imageViewGoster.setImageResource(R.drawable.user2)
            binding.txtAvatarNick.text = "Yorumlayıcı"
        }
        binding.imageViewWomen4.setOnClickListener {
            binding.imageViewGoster.setImageResource(R.drawable.woman2)
            binding.txtAvatarNick.text = "Filmkolik"
        }
        binding.imageViewMan1.setOnClickListener {
            binding.imageViewGoster.setImageResource(R.drawable.userrr)
            binding.txtAvatarNick.text = "Film Delisi"
        }
        binding.imageViewMan2.setOnClickListener {
            binding.imageViewGoster.setImageResource(R.drawable.gamer)
            binding.txtAvatarNick.text = "Film Canavarı"
        }
        binding.imageViewMan3.setOnClickListener {
            binding.imageViewGoster.setImageResource(R.drawable.man)
            binding.txtAvatarNick.text = "Film Muhtarı"

        }
        binding.imageViewMan4.setOnClickListener {
            binding.imageViewGoster.setImageResource(R.drawable.mann)
            binding.txtAvatarNick.text = "Çılgın izleyici"
        }

        binding.button2.setOnClickListener {
            val user = Firebase.auth.currentUser
            val newPassword = binding.editTextsifre.text.toString()

            if (user != null && newPassword.isNotEmpty()) {
                val credential = EmailAuthProvider.getCredential(
                    user.email!!,
                    binding.editTextKullanici.text.toString()
                )
                user.reauthenticate(credential).addOnCompleteListener { reAuthTask ->
                    if (reAuthTask.isSuccessful) {
                        user.updatePassword(newPassword).addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                Toast.makeText(
                                    requireContext(),
                                    "Şifre güncellendi.",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Şifre güncellenirken bir hata oluştu!.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(requireContext(), "Mevcut şifre yanlış!", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } else {
                Snackbar.make(it , "Boş Geçilemez", Snackbar.LENGTH_SHORT).show()
            }

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}