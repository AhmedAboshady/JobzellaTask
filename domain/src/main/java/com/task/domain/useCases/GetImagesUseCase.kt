package com.task.domain.useCases

import com.task.domain.entity.Images
import com.task.domain.entity.request.GetImagesRequest
import com.task.domain.helper.Interactor
import com.task.domain.repo.ImagesRepo
import kotlinx.coroutines.flow.Flow

class GetImagesUseCase(private val mImageRepo: ImagesRepo):
    Interactor<GetImagesRequest, Flow<Images>> {
    override fun execute(params: GetImagesRequest)=
        mImageRepo.getImagesFromRemote(client_id = params.client_id, query = params.query, pageSize = params.pageSize)
}