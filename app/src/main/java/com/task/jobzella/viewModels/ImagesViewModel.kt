package com.task.jobzella.viewModels

import androidx.lifecycle.viewModelScope
import com.task.domain.entity.request.GetImagesRequest
import com.task.domain.useCases.GetImagesUseCase
import com.task.jobzella.helper.SingleLiveEvent
import com.task.jobzella.helper.io
import com.task.jobzella.helper.ui

import com.task.jobzella.base.BaseViewModel
import com.task.jobzella.helper.MapThrowableToMTFailure

import com.task.jobzella.viewModels.state.GetImagesVS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImagesViewModel(private val mImagesUseCase: GetImagesUseCase) : BaseViewModel() {
    private val mViewState = SingleLiveEvent<GetImagesVS>()
    val viewState: SingleLiveEvent<GetImagesVS> get() = mViewState
    fun getImages(query: String,pageSize:String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                io {
                    mImagesUseCase.execute(
                        GetImagesRequest(
                            client_id = "mJ4a5MWVxBb-_yfQIqzGZoU9E2wJiIvB_go30hgW2fI",
                            query = query,
                            pageSize = pageSize
                        )
                    ).collect {
                        ui {
                            if (it.results.isEmpty()){
                                viewState.value = GetImagesVS.EmptyResult
                                return@ui
                            }
                            viewState.value = GetImagesVS.Success(it.results)
                        }
                    }
                }
            } catch (e: Exception) {

                    ui {
                        val error = MapThrowableToMTFailure.map(e)
                        if (error.errors != null)
                            mViewState.value = GetImagesVS.Error(error.errors[0])
                        if (error.errorMessageResId != null)
                            mViewState.value = GetImagesVS.ServerError(error.errorMessageResId!!)
                    }

            }
        }
    }
    /**
     *  validate query  (empty/minLength)..
     */
fun validate(query:String,minLength:Int): Boolean{
    var case = true
     if (query.isEmpty()) {
       mViewState.value=GetImagesVS.EmptyField
          case = false
    } else if (query.length<minLength)
    {
        mViewState.value=GetImagesVS.LengthError(minLength)
        case = false
    }
    return case
}
}