package com.task.repoImpl

import com.task.domain.entity.Images
import com.task.domain.repo.ImagesRepo
import com.task.rest.ImagesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeImagesRepoImpl(): ImagesRepo {
    override fun getImagesFromRemote(client_id: String, query: String, pageSize: String): Flow<Images> {
        return flow {

        }
    }

}

