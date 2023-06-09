package com.task.jobzella.helper


import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import com.google.gson.Gson
import com.task.jobzella.R

var sessionExpired= SingleLiveEvent<Boolean>()
object MapThrowableToMTFailure {
    fun map(throwable: Throwable): AppFailure {

        return try {

            when (throwable) {
                is HttpException -> {
                    val httpException: HttpException = throwable
                    when (httpException.code()) {
                        401,400 -> {
                            Gson().fromJson(
                                throwable.response()?.errorBody()?.charStream(),
                                AppFailure::class.java
                            )
                        }
                        503,500 -> {
                            return ServiceUnAvailable()
                        }
                        else -> {
                            UnKnownError()
                        }
                    }
                }
                is SocketTimeoutException -> {

                    return TimeOut()
                }
                is UnknownHostException -> {

                    return ConnectionError()
                }
                is IOException -> {
                    return ConnectionError()
                }
                else -> {

                    return UnKnownError()
                }
            }
        } catch (e: Exception) {

            e.printStackTrace()
            return UnKnownError()
        }
    }
}

open class  AppFailure {
    val errors: MutableList<String>? = null
    var errorMessageResId: Int? = null
}


class ServiceUnAvailable : AppFailure() {
    init {
        errorMessageResId = R.string.service_not_available
    }
}

class TimeOut : AppFailure() {
    init {
        errorMessageResId = R.string.connection_time_out
    }
}




class ConnectionError : AppFailure() {
    init {
        errorMessageResId = R.string.no_connection
    }
}

class UnKnownError : AppFailure() {
    init {

        errorMessageResId = R.string.unknown_error
    }
}

