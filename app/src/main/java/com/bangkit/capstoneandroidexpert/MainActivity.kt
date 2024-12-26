package com.bangkit.capstoneandroidexpert

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bangkit.core.data.ProductRepository
import com.bangkit.capstoneandroidexpert.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var repository : com.bangkit.core.data.ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("repository", repository.toString())

        val navController = findNavController(R.id.fragment_container)
        val bottomNavigationView = binding.bottomNavigation

        val toolbar = binding.topAppBar
        setSupportActionBar(toolbar)

        navController.addOnDestinationChangedListener{_, destination, _ ->
            supportActionBar?.title = destination.label
        }

        bottomNavigationView.setupWithNavController(navController)

    }
}