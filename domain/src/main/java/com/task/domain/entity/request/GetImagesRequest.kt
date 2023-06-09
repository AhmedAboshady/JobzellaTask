package com.task.domain.entity.request


data class GetImagesRequest(
    var client_id:String="",
    var query:String="",
    var pageSize:String="10",
    )