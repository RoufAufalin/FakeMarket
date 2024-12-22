package com.bangkit.capstoneandroidexpert.presentation.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.capstoneandroidexpert.R
import com.bangkit.capstoneandroidexpert.databinding.FragmentFavoriteBinding
import com.bangkit.capstoneandroidexpert.presentation.detail.DetailActivity
import com.bangkit.capstoneandroidexpert.presentation.home.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.bangkit.core.data.Result

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        favoriteViewModel.favoriteData.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                adapter.submitList(data)
                binding.progressBar.visibility = View.GONE
            } else {
                Toast.makeText(context, "Data not found", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

}