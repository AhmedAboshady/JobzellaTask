package com.task.rest


import com.task.remote.ImagesApiClient

import kotlinx.coroutines.flow.flow


class ImagesDataStore(private val apiClient: ImagesApiClient) {
    fun getImages(client_id: String, query: String, pageSize: String) = flow {
      emit(apiClient.getImages(client_id,query,pageSize))
    }

}