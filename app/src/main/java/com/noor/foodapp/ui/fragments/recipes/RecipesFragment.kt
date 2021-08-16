package com.noor.foodapp.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.noor.foodapp.viewmodels.MainViewModel
import com.noor.foodapp.adapters.RecipesAdapter
import com.noor.foodapp.databinding.FragmentRecipesBinding
import com.noor.foodapp.util.Constants.Companion.API_KEY
import com.noor.foodapp.util.NetworkResult
import com.noor.foodapp.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
    private val mAdapter by lazy {RecipesAdapter()}
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        setupRecyclerView()
        requestApiData()

        return binding.root
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        Log.d("VAY","Request Api Olusturuldu")
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            Log.d("VAY","Observe Edildi.")
            when(response) {
                is NetworkResult.Success -> {
                    Log.d("VAY","Network result basarili")
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    Log.d("VAY","Network result basarisiz")
                    Log.d("VAY","Error : ${response.message.toString()}")
                    hideShimmerEffect()
                    Toast.makeText(requireContext(),response.message.toString(), Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading -> {
                    Log.d("VAY","Network result yukleniyor")
                    showShimmerEffect()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding.recyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.recyclerView.hideShimmer()
    }

}