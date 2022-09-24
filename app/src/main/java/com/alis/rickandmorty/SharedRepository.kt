package com.alis.rickandmorty

import com.alis.rickandmorty.model.Character
import com.alis.rickandmorty.network.NetworkLayer

class SharedRepository {
    suspend fun getCharacterById(id: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(id = id)

        if (request.isFailed) return null

        if (!request.isSuccessful) return null

        return request.body
    }
}