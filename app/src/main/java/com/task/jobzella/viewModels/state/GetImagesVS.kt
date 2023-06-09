package com.task.jobzella.viewModels.state

sealed class GetImagesVS{
 class Success(val result:List<com.task.domain.entity.Result>)  : GetImagesVS()
 object EmptyResult  : GetImagesVS()
 class LengthError(val minLength:Int) : GetImagesVS()
 object EmptyField : GetImagesVS()
 class Error(val message: String) : GetImagesVS()
 class ServerError(val errorMessageResId: Int) : GetImagesVS()

}
