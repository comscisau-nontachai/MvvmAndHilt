package com.feyverly.mvvmandhilt.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.feyverly.mvvmandhilt.data.repository.CharacterRepositoty
import javax.inject.Inject

class CharacterViewModel @ViewModelInject constructor(
    private val repositoty: CharacterRepositoty
) : ViewModel(){
    val character = repositoty.getAllCharacters()
}