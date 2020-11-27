package com.feyverly.mvvmandhilt.ui.character_detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.feyverly.mvvmandhilt.data.repository.CharacterRepositoty

class CharacaterDetailViewModel @ViewModelInject constructor(
    private val repositoty: CharacterRepositoty
) : ViewModel(){

    private val _id = MutableLiveData<Int>()
    private val _character = _id.switchMap { id -> repositoty.getCharacter(id) }
    val character = _character
    
    fun start(id : Int){
        _id.value = id
    }
}