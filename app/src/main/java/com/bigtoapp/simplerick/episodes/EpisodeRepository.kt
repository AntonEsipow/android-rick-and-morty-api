package com.bigtoapp.simplerick.episodes

import com.bigtoapp.simplerick.domain.mappers.EpisodeMapper
import com.bigtoapp.simplerick.domain.models.Episode
import com.bigtoapp.simplerick.network.NetworkLayer
import com.bigtoapp.simplerick.network.response.GetCharacterByIdResponse
import com.bigtoapp.simplerick.network.response.GetEpisodeByIdResponse
import com.bigtoapp.simplerick.network.response.GetEpisodesPageResponse

class EpisodeRepository {

    suspend fun fetchEpisodesPage(pageIndex: Int): GetEpisodesPageResponse? {
        val pageRequest = NetworkLayer.apiClient.getEpisodesPage(pageIndex)

        if(!pageRequest.isSuccessful) {
            return null
        }

        return pageRequest.body
    }

    suspend fun getEpisodeById(episodeId: Int): Episode? {

        val request = NetworkLayer.apiClient.getEpisodeById(episodeId)

        if (!request.isSuccessful) {
            return null
        }

        val characterList = getCharactersFromEpisodeResponse(request.body)
        return EpisodeMapper.buildFrom(
            networkEpisode = request.body,
            networkCharacters = characterList
        )
    }

    private suspend fun getCharactersFromEpisodeResponse(
        episodeByIdResponse: GetEpisodeByIdResponse
    ): List<GetCharacterByIdResponse>{
        val characterList = episodeByIdResponse.characters.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }
        val request = NetworkLayer.apiClient.getMultipleCharacters(characterList)
        return request.bodyNullable ?: emptyList()
    }
}