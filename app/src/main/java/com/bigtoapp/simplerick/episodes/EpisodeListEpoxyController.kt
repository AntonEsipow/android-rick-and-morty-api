package com.bigtoapp.simplerick.episodes

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.bigtoapp.simplerick.R
import com.bigtoapp.simplerick.databinding.ModelEpisodeListItemBinding
import com.bigtoapp.simplerick.databinding.ModelEpisodeListTitleBinding
import com.bigtoapp.simplerick.domain.models.Episode
import com.bigtoapp.simplerick.epoxy.ViewBindingKotlinModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class EpisodeListEpoxyController(
    private val onEpisodeClicked: (Int) -> Unit
) : PagingDataEpoxyController<EpisodesUiModel>() {

    override fun buildItemModel(currentPosition: Int, item: EpisodesUiModel?): EpoxyModel<*> {
        return when (item!!) {
            is EpisodesUiModel.Item -> {
                val episode = (item as EpisodesUiModel.Item).episode
                EpisodeListItemEpoxyModel(
                    episode = episode,
                    onClick = { episodeId ->
                        onEpisodeClicked(episodeId)
                    }
                ).id("episode_${episode.id}")
            }
            is EpisodesUiModel.Header -> {
                val headerText = (item as EpisodesUiModel.Header).text
                EpisodeListTitleEpoxyModel(headerText).id("header_$headerText")
            }
        }
    }

    data class EpisodeListItemEpoxyModel(
        val episode: Episode,
        val onClick: (Int) -> Unit
    ) : ViewBindingKotlinModel<ModelEpisodeListItemBinding>(R.layout.model_episode_list_item) {

        override fun ModelEpisodeListItemBinding.bind() {
            episodeNameTextView.text = episode.name
            episodeAirDateTextView.text = episode.airDate
            episodeNumberTextView.text = episode.getFormattedSeasonTruncated()

            root.setOnClickListener { onClick(episode.id) }
        }
    }

    data class EpisodeListTitleEpoxyModel(
        val title: String
    ): ViewBindingKotlinModel<ModelEpisodeListTitleBinding>(R.layout.model_episode_list_title) {

        override fun ModelEpisodeListTitleBinding.bind() {
            textView.text = title
        }
    }
}