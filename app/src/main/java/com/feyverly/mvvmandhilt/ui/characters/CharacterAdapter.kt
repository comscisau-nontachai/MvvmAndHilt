package com.feyverly.mvvmandhilt.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.feyverly.mvvmandhilt.data.entities.Character
import com.feyverly.mvvmandhilt.databinding.ItemRvCharacterBinding

class CharacterAdapter constructor(private val listener: OnCharacterListener) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val items = ArrayList<Character>()

    interface OnCharacterListener {
        fun onItemClicked(id: Int)
    }

    inner class CharacterViewHolder(val binding: ItemRvCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character, listener: OnCharacterListener) {
            binding.apply {
                data = item
                action = listener
                executePendingBindings()
            }
        }
    }

    fun setItems(list: ArrayList<Character>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemRvCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size ?: 0
}