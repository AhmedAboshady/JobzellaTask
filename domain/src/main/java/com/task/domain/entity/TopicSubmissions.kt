package com.task.domain.entity

import android.os.Parcelable
import com.task.domain.entity.Wallpapers
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopicSubmissions(
    val wallpapers: Wallpapers
): Parcelable