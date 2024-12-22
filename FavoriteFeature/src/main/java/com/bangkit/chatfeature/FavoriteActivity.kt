package com.bangkit.chatfeature

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.capstoneandroidexpert.di.FavoriteModuleDependencies
import com.bangkit.capstoneandroidexpert.presentation.detail.DetailActivity
import com.bangkit.capstoneandroidexpert.presentation.home.HomeAdapter
import com.bangkit.chatfeature.databinding.ActivityChatBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: CartViewModel by viewModels {
        factory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.topAppBar)

        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

        val adapter = HomeAdapter()
        adapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        with(binding.rv) {
            layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        viewModel.fav.observe(this) {
            adapter.submitList(it)
            Log.d("TAG", "onCreate: $it")
        }

    }
}