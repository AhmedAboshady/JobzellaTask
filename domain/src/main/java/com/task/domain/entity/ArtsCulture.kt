package com.task.domain.entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtsCulture(
    val approved_on: String,
    val status: String
): Parcelable