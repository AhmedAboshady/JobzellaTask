package com.task.jobzella.viewModels


import com.google.common.truth.Truth.assertThat
import com.task.domain.entity.Images
import com.task.domain.entity.request.GetImagesRequest
import com.task.domain.helper.Interactor
import com.task.domain.repo.ImagesRepo
import com.task.domain.useCases.GetImagesUseCase
import com.task.repoImpl.FakeImagesRepoImpl
import com.task.repoImpl.ImagesRepoImpl
import com.task.rest.ImagesDataStore
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Test

class ImagesViewModelTest{
private lateinit var mViewModel: ImagesViewModel
  @Before
  fun setUp(){
      mViewModel= ImagesViewModel(GetImagesUseCase(FakeImagesRepoImpl()))

  }
    @Test
    fun emptyQueryReturnFalse(){
  val result=mViewModel.validate("a",2)
       assertThat(result).isTrue()
    }
    @Test
    fun minLengthQueryReturnFalse(){

        val result=mViewModel.validate("cat",3)
        assertThat(result).isTrue()
    }
}