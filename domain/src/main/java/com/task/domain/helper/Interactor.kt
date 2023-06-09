package com.task.domain.helper

interface Interactor<in Params, out Type> {

    fun execute(params: Params): Type

    object None
}