package com.alis.rickandmorty

import com.airbnb.epoxy.EpoxyController
import com.alis.rickandmorty.databinding.ModelCharacterDetailsDataPointBinding
import com.alis.rickandmorty.databinding.ModelCharacterDetailsHeaderBinding
import com.alis.rickandmorty.databinding.ModelCharacterDetailsImageBinding
import com.alis.rickandmorty.epoxy.LoadingEpoxyModel
import com.alis.rickandmorty.epoxy.ViewBindingKotlinModel
import com.alis.rickandmorty.model.Character
import com.squareup.picasso.Picasso

class CharacterDetailsEpoxyController : EpoxyController() {
    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field)
                requestModelBuild()
        }

    var response: Character? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        response?.let { character ->
            HeaderEpoxyModel(name = character.name, status = character.status).id("header").addTo(this)
            ImageEpoxyModel(url = character.image).id("image").addTo(this)
            DataPointEpoxyModel(title = "Origin", description = character.origin.name).id("originDataPoint").addTo(this)
            DataPointEpoxyModel(title = "Species", description = character.species).id("speciesDataPoint").addTo(this)
        }
    }

    data class HeaderEpoxyModel(val name: String, val status: String) :
        ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header) {
        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status
        }
    }

    data class ImageEpoxyModel(val url: String) : ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image) {
        override fun ModelCharacterDetailsImageBinding.bind() {
            Picasso.get().load(url).into(headerImage)
        }
    }

    data class DataPointEpoxyModel(val title: String, val description: String) :
        ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point) {
        override fun ModelCharacterDetailsDataPointBinding.bind() {
            titleTextView.text = title
            descriptionTextView.text = description
        }
    }
}