package com.task.repoImpl

import com.task.domain.repo.ImagesRepo
import com.task.rest.ImagesDataStore

class ImagesRepoImpl(private val authDataStore: ImagesDataStore): ImagesRepo {
    override fun getImagesFromRemote(client_id: String, query: String, pageSize: String)
    = authDataStore.getImages(client_id,query,pageSize)

}

