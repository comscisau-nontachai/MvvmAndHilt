package com.feyverly.mvvmandhilt.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.feyverly.mvvmandhilt.data.entities.Character
import com.feyverly.mvvmandhilt.databinding.ItemRvCharacterBinding

class CharacterAdapter(val listener: OnItemCharacterListener) :
    RecyclerView.Adapter<CharacterViewHolder>() {
    private val items = ArrayList<Character>()

    interface OnItemCharacterListener {
        fun onItemClicked(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemRvCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    open fun setItem(list: ArrayList<Character>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }
}

class CharacterViewHolder(
    val itemBinding: ItemRvCharacterBinding,
    val listener: CharacterAdapter.OnItemCharacterListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {
    private lateinit var character: Character

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Character) {
        this.character = item
        itemBinding.tvName.text = item.name
        itemBinding.tvSpeciesAndStatus.text = """${item.species} - ${item.status}"""
        Glide.with(itemBinding.root).load(item.image).transform(CircleCrop())
            .into(itemBinding.imgImage)
    }

    override fun onClick(v: View?) {
        listener.onItemClicked(character.id)
    }
}
