package com.feyverly.mvvmandhilt.data.remote

import com.feyverly.mvvmandhilt.data.base.BaseDataSource
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val api : CharacterService
) : BaseDataSource(){

    suspend fun getCharacters() = getResults { api.getAllCharacters() }
    suspend fun getCharacter(id : Int) = getResults { api.getCharacter(id)}
}