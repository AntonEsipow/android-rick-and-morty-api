package com.bigtoapp.simplerick.characters.search

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.bigtoapp.simplerick.R
import com.bigtoapp.simplerick.databinding.ModelCharacterListItemBinding
import com.bigtoapp.simplerick.epoxy.LoadingEpoxyModel
import com.bigtoapp.simplerick.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso
import com.bigtoapp.simplerick.domain.models.Character
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class CharacterSearchEpoxyController(
    private val onCharacterSelected: (Int) -> Unit
): PagingDataEpoxyController<Character>() {

    override fun buildItemModel(currentPosition: Int, item: Character?): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(
            characterId = item!!.id,
            imageUrl = item.image,
            name = item.name,
            onCharacterSelected = { characterId ->
                onCharacterSelected(characterId)
            }
        ).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        if (models.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        super.addModels(models)
    }

    data class CharacterGridItemEpoxyModel(
        val characterId: Int,
        val imageUrl: String,
        val name: String,
        val onCharacterSelected: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item) {

        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name

            root.setOnClickListener {
                onCharacterSelected(characterId)
            }
        }
    }
}