package com.feyverly.mvvmandhilt.ui.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.feyverly.mvvmandhilt.R
import com.feyverly.mvvmandhilt.data.base.BaseFragment
import com.feyverly.mvvmandhilt.databinding.FragmentCharacterBinding
import com.feyverly.mvvmandhilt.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.awaitAll
import timber.log.Timber


@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>(),CharacterAdapter.OnCharacterListener {

    private val viewModel: CharacterViewModel by viewModels()
    private lateinit var adapter: CharacterAdapter

    private fun setupRecyclerView(){
        adapter = CharacterAdapter(this)
        binding.rvCharacters.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCharacters.adapter = adapter
    }

    override fun setupObservers(){
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.isGone
                    it.data?.let { adapter.setItems(ArrayList(it)) }
                }
                Resource.Status.LOADING -> binding.progressBar.isVisible
                Resource.Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }



    override fun getLayoutId(): Int = R.layout.fragment_character

    override fun initView() {
        setupRecyclerView()
    }

    override fun initListener() {
    }

    override fun onItemClicked(id: Int) {
        findNavController().navigate(R.id.action_characterFragment_to_characterDetailFragment,
            bundleOf("id" to id))
    }


}