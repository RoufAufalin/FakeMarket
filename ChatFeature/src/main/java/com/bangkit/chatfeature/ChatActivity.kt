package com.bangkit.chatfeature

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.capstoneandroidexpert.di.CartModuleDependencies
import com.bangkit.capstoneandroidexpert.presentation.detail.DetailActivity
import com.bangkit.capstoneandroidexpert.presentation.favorite.FavoriteViewModel
import com.bangkit.capstoneandroidexpert.presentation.home.HomeAdapter
import com.bangkit.chatfeature.databinding.ActivityChatBinding
import dagger.hilt.android.EntryPointAccessors
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import javax.inject.Inject

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: CartViewModel by viewModels {
        factory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCartComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    CartModuleDependencies::class.java
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
            layoutManager = GridLayoutManager(this@ChatActivity, 2)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        viewModel.cart.observe(this) {
            adapter.submitList(it)
            Log.d("TAG", "onCreate: $it")
        }

    }
}