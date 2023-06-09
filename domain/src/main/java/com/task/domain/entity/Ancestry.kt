package com.task.domain.entity
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Ancestry(
    val category: Category,
    val subcategory: Subcategory,
    val type: Type
): Parcelable