package com.bigtoapp.simplerick.episodes.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigtoapp.simplerick.domain.models.Episode
import com.bigtoapp.simplerick.episodes.EpisodeRepository
import kotlinx.coroutines.launch

class EpisodeDetailViewModel: ViewModel() {

    private val repository = EpisodeRepository()

    private val _episodeLiveData = MutableLiveData<Episode?>()
    val episodeLiveData: LiveData<Episode?> get() = _episodeLiveData

    fun fetchEpisode(episodeId: Int) {
        viewModelScope.launch {
            val episode = repository.getEpisodeById(episodeId)

            _episodeLiveData.postValue(episode)
        }
    }
}