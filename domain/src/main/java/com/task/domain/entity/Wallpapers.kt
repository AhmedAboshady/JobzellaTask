package com.task.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wallpapers(
    val approved_on: String,
    val status: String
): Parcelable