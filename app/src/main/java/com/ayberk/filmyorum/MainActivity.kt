package com.ayberk.filmyorum

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.filmyorum.adapter.MovieAdapter
import com.ayberk.filmyorum.adapter.RecentMovieAdapter
import com.ayberk.filmyorum.databinding.ActivityMainBinding
import com.ayberk.filmyorum.di.retrofit.models.Movie
import com.ayberk.filmyorum.viewmodel.HomePageViewModel
import android.view.View
import android.view.WindowManager
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.Result as Result


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var sarjSeviyeAlgilama: SarjSeviyeAlgilama

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        sarjSeviyeAlgilama = SarjSeviyeAlgilama()
    }

    override fun onResume() {
        super.onResume()

        val filter = IntentFilter()
        filter.addAction("android.intent.action.BATTERY_LOW")
        registerReceiver(sarjSeviyeAlgilama,filter)
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(sarjSeviyeAlgilama)
    }
}

