package com.task.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Social(
    val instagram_username: String,

    val portfolio_url: String,
    val twitter_username: String
): Parcelable