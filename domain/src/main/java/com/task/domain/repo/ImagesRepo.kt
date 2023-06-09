package com.task.domain.repo

import com.task.domain.entity.Images
import kotlinx.coroutines.flow.Flow

interface ImagesRepo {
    fun getImagesFromRemote(client_id:String,query:String, pageSize: String): Flow<Images>
}