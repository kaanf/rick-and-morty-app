package com.alis.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alis.rickandmorty.model.Character
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val repository: SharedRepository = SharedRepository()

    private val characterById = MutableLiveData<Character?>()
    val characterByIdHolder: LiveData<Character?>
        get() = characterById

    fun updateCharacter(id: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(id = id)
            characterById.postValue(response)
        }
    }
}