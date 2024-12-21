package com.bangkit.capstoneandroidexpert.presentation.detail

import android.content.Context
import android.nfc.NfcAdapter.EXTRA_DATA
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.capstoneandroidexpert.R
import com.bangkit.capstoneandroidexpert.databinding.ActivityDetailBinding
import com.bangkit.core.domain.model.Product
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)


        val product = getParcelableExtra(intent, EXTRA_DATA, Product::class.java)

        if (product != null) {
            setUpData(product)
        } else {
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show()
        }

        statusFavorite(product!!.favorite)

        binding.btnBuy.setOnClickListener {
            showConfirmPurchase(product)
        }

        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

    }



    private fun setUpData(product: Product){
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(product.image)
                .into(ivProduct)
            tvProductTitle.text = product.title
            tvCategory.text = product.category
            tvRating.text = product.rating.toString()
            tvCount.text = getString(R.string.product_count, product.rate)
            tvPrice.text = getString(R.string.dollar_value, product.price)
            tvDesc.text = product.description
        }

        var favorite = product.favorite

        binding.ivFavorite.setOnClickListener {
            favorite = !favorite
            viewModel.setFavorite(product, favorite)
            statusFavorite(favorite)
        }

    }

    private fun statusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.favorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
        } else {
            binding.favorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
        }
    }

    private fun showConfirmPurchase(product: Product) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.confirmation_dialog, null)
        val btnYes = dialogView.findViewById<View>(R.id.btn_yes)
        val btnCancel = dialogView.findViewById<View>(R.id.btn_no)

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        btnYes.setOnClickListener {
            viewModel.setCart(product, true)
            alertDialog.dismiss()
        }

        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }


    companion object {
        const val EXTRA_DATA = "extra_product"
    }
}