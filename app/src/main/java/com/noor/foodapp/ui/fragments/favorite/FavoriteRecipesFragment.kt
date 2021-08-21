package com.noor.foodapp.ui.fragments.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noor.foodapp.adapters.FavoriteRecipesAdapter
import com.noor.foodapp.databinding.FragmentFavoriteRecipesBinding
import com.noor.foodapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteRecipesBinding
    private val mAdapter: FavoriteRecipesAdapter by lazy {FavoriteRecipesAdapter()}
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteRecipesBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter

        setupRecyclerView(binding.favoriteRecipesRecyclerView)

        return binding.root
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}