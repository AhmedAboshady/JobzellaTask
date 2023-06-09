package com.task.remote

import com.task.domain.entity.Images
import retrofit2.http.GET
import retrofit2.http.Query


interface ImagesApiClient {
   @GET("/search/photos")
   suspend fun getImages(@Query("client_id") clientId:String,@Query("query") query:String,@Query("per_page") size:String): Images

}