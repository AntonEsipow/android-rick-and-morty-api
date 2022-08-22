package com.bigtoapp.simplerick.episodes

import com.bigtoapp.simplerick.network.NetworkLayer
import com.bigtoapp.simplerick.network.response.GetEpisodesPageResponse

class EpisodeRepository {

    suspend fun fetchEpisodesPage(pageIndex: Int): GetEpisodesPageResponse? {
        val pageRequest = NetworkLayer.apiClient.getEpisodesPage(pageIndex)

        if(!pageRequest.isSuccessful) {
            return null
        }

        return pageRequest.body
    }
}