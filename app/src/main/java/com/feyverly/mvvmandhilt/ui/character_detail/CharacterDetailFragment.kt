package com.feyverly.mvvmandhilt.ui.character_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.feyverly.mvvmandhilt.R
import com.feyverly.mvvmandhilt.data.base.BaseFragment
import com.feyverly.mvvmandhilt.data.entities.Character
import com.feyverly.mvvmandhilt.databinding.FragmentCharacterDetailBinding
import com.feyverly.mvvmandhilt.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding>() {

    private val viewModel : CharacaterDetailViewModel by viewModels()

    override fun setupObservers(){
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.isLoading = false
                    binding.data = it.data
                }
                Resource.Status.LOADING -> binding.isLoading = true
                Resource.Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


    override fun getLayoutId(): Int = R.layout.fragment_character_detail

    override fun initView() {
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }
    override fun initListener() {
    }
}