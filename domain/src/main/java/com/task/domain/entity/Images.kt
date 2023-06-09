package com.task.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
): Parcelable