package com.bigtoapp.simplerick

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigtoapp.simplerick.domain.models.Character
import com.bigtoapp.simplerick.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository: SharedRepository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<Character>()
    val characterByIdLiveData: LiveData<Character> get() = _characterByIdLiveData

    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)

            _characterByIdLiveData.postValue(response)
        }
    }
}