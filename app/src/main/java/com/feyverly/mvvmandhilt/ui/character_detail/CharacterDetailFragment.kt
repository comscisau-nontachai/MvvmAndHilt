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
import com.feyverly.mvvmandhilt.data.entities.Character
import com.feyverly.mvvmandhilt.databinding.FragmentCharacterDetailBinding
import com.feyverly.mvvmandhilt.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private lateinit var binding : FragmentCharacterDetailBinding
    private val viewModel : CharacaterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }
    private fun setupObservers(){
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.isGone
                    bindCharacter(it.data!!)
                }
                Resource.Status.LOADING -> binding.progressBar.isVisible
                Resource.Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
    private fun bindCharacter(character: Character){
        binding.apply {
            tvName.text = character.name
            tvSpecies.text = character.species
            tvStatus.text = character.status
            tvGender.text = character.gender
            imgImage.loadImage(character.image)
        }

    }
    fun ImageView.loadImage(url:String){
        Glide.with(requireContext()).load(url).transform(CircleCrop()).into(this)
    }
}