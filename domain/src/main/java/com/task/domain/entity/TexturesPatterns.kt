package com.task.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TexturesPatterns(
    val approved_on: String,
    val status: String
): Parcelable