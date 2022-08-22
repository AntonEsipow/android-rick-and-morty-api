package com.bigtoapp.simplerick.network.response

import android.icu.text.IDNA

data class GetCharactersPageResponse(
    val info: PageInfo = PageInfo(),
    val results: List<GetCharacterByIdResponse> = emptyList()
)