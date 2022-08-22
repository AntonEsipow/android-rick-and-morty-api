package com.bigtoapp.simplerick.network.response

import android.graphics.pdf.PdfDocument

data class GetEpisodesPageResponse(
    val info: PageInfo = PageInfo(),
    val results: List<GetEpisodeByIdResponse> = emptyList()
)