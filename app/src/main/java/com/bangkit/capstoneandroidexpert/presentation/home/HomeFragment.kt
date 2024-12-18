package com.bangkit.capstoneandroidexpert.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.capstoneandroidexpert.databinding.FragmentHomeBinding
import com.bangkit.capstoneandroidexpert.presentation.detail.DetailActivity
import com.bangkit.core.data.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeAdapter()

        with(binding.rvProduct) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            this.adapter = adapter

            adapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                    .putExtra(DetailActivity.EXTRA_DATA, it)
                startActivity(intent)
            }
        }

        binding.chat.setOnClickListener {
            val uri = Uri.parse("fakemarket://chat")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }



        homeViewModel.product.observe(viewLifecycleOwner) { product ->
            if (product != null) {
                when(product) {
                    is com.bangkit.core.data.Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.submitList(product.data)
                    }
                    is com.bangkit.core.data.Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is com.bangkit.core.data.Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}