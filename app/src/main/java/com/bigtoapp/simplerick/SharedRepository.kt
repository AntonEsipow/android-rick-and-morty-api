package com.bigtoapp.simplerick

import com.bigtoapp.simplerick.domain.mappers.CharacterMapper
import com.bigtoapp.simplerick.domain.models.Character
import com.bigtoapp.simplerick.network.NetworkLayer
import com.bigtoapp.simplerick.network.response.GetCharacterByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if(request.failed) {
            return null
        }

        if(!request.isSuccessful) {
            return  null
        }

        return CharacterMapper.buildFrom(response = request.body)
    }
}