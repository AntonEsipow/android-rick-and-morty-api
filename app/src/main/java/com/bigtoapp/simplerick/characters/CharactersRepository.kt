package com.bigtoapp.simplerick.characters

import com.bigtoapp.simplerick.network.NetworkLayer
import com.bigtoapp.simplerick.network.response.GetCharactersPageResponse

class CharactersRepository {

    suspend fun getCharactersPage(pageIndex: Int): GetCharactersPageResponse? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }
}