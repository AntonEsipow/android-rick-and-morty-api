package com.bigtoapp.simplerick.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigtoapp.simplerick.characters.CharactersRepository
import com.bigtoapp.simplerick.domain.models.Character
import com.bigtoapp.simplerick.network.SimpleRickCache
import kotlinx.coroutines.launch

class CharacterDetailViewModel: ViewModel() {

    private val repository: CharactersRepository = CharactersRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()
    val characterByIdLiveData: LiveData<Character?> get() = _characterByIdLiveData

    fun fetchCharacter(characterId: Int) = viewModelScope.launch {
        val character = repository.getCharacterById(characterId)
        _characterByIdLiveData.postValue(character)
    }
}