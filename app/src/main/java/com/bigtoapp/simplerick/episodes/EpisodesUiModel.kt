package com.bigtoapp.simplerick.episodes

import com.bigtoapp.simplerick.domain.models.Episode

sealed class EpisodesUiModel {

    class Item(val episode: Episode): EpisodesUiModel()
    class Header(val text: String): EpisodesUiModel()
}