package com.noor.foodapp.ui.fragments.foodjoke

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.noor.foodapp.databinding.FragmentFoodJokeBinding
import com.noor.foodapp.util.Constants.Companion.API_KEY
import com.noor.foodapp.util.NetworkResult
import com.noor.foodapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodJokeFragment : Fragment() {

    private lateinit var binding: FragmentFoodJokeBinding
    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFoodJokeBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.mainViewModel = mainViewModel

        mainViewModel.getFoodJoke(API_KEY)
        mainViewModel.foodJokeResponse.observe(viewLifecycleOwner, { response ->
            when(response) {
                is NetworkResult.Success -> {
                    binding.foodJokeTextView.text = response.data?.text
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    Log.d("FoodJokeFragment", "Loading")
                }
            }
        })

        return binding.root
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readFoodJoke.observe(viewLifecycleOwner, { database ->
                if(database.isNotEmpty() && database != null) {
                    binding.foodJokeErrorTextView.text = database[0].foodJoke.text
                }
            })
        }
    }

}