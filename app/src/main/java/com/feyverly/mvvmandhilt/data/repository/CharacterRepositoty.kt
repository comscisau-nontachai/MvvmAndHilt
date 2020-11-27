package com.feyverly.mvvmandhilt.data.repository

import com.feyverly.mvvmandhilt.data.local.CharacterDao
import com.feyverly.mvvmandhilt.data.remote.CharacterRemoteDataSource
import com.feyverly.mvvmandhilt.utils.performGetOperation
import javax.inject.Inject

class CharacterRepositoty @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource : CharacterDao
){

    fun getAllCharacters() = performGetOperation(
        databaseQuery = {localDataSource.getAllCharacters()},
        networkCall = {remoteDataSource.getCharacters()},
        saveCallResult = {localDataSource.insertAll(it.results)}
    )

    fun getCharacter(id : Int) = performGetOperation(
        databaseQuery = {localDataSource.getCharacter(id)},
        networkCall = {remoteDataSource.getCharacter(id)},
        saveCallResult = {localDataSource.insert(it)}
    )
}