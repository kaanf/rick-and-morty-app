package com.alis.rickandmorty.network

import com.alis.rickandmorty.model.Character
import retrofit2.Response

class APIClient(private val service: NetworkService) {
    suspend fun getCharacterById(id: Int): IOStatus<Character> = safeAPICall { service.getCharacterById(id = id) }

    private inline fun <T> safeAPICall(apiCall: () -> Response<T>): IOStatus<T> {
        return try {
            IOStatus.isSuccess(data = apiCall.invoke())
        } catch (exception: Exception) {
            IOStatus.isFailure(exception = exception)
        }
    }
}