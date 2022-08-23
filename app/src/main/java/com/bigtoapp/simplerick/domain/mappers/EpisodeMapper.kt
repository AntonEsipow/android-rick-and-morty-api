package com.bigtoapp.simplerick.domain.mappers

import com.bigtoapp.simplerick.domain.models.Episode
import com.bigtoapp.simplerick.network.response.GetCharacterByIdResponse
import com.bigtoapp.simplerick.network.response.GetEpisodeByIdResponse

object EpisodeMapper {

    fun buildFrom(
        networkEpisode: GetEpisodeByIdResponse,
        networkCharacters: List<GetCharacterByIdResponse> = emptyList()
    ): Episode {
        return Episode(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.air_date,
            seasonNumber = getSeasonNumberFromEpisodeString(networkEpisode.episode),
            episodeNumber = getEpisodeNumberFromEpisodeString(networkEpisode.episode),
            characters = networkCharacters.map {
                CharacterMapper.buildFrom(it)
            }
        )
    }

    fun getSeasonNumberFromEpisodeString(episode: String): Int {
        val endIndex = episode.indexOfFirst { it.equals('e', true) }
        if(endIndex == -1) {
            return  0
        }
        return  episode.substring(1, endIndex).toIntOrNull() ?: 0
    }

    private fun getEpisodeNumberFromEpisodeString(episode: String): Int {
        val startIndex = episode.indexOfFirst { it.equals('e', true) }
        if (startIndex == -1) {
            return 0
        }
        return episode.substring(startIndex + 1).toIntOrNull() ?: 0
    }
}