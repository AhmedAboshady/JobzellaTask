package com.task.jobzella.di


import com.task.remote.ImagesApiClient
import com.task.repoImpl.ImagesRepoImpl
import com.task.rest.ImagesDataStore
import com.task.domain.repo.ImagesRepo
import com.task.domain.useCases.GetImagesUseCase
import com.task.jobzella.viewModels.ImagesViewModel
import com.task.jobzella.BuildConfig


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit




private val ImagesModule = module {
    single  { ImagesViewModel(get()) }
    single { GetImagesUseCase(get()) }
    single <ImagesRepo>{ ImagesRepoImpl(get())  }
    single { ImagesDataStore(get()) }


}
val networkModule = module {

    factory { provideApiServices(get()) }

    single { provideRetrofit() }
}
fun provideRetrofit(): Retrofit {
    val CONNECTION_TIMEOUT: Long = 180L
    val READ_TIMEOUT: Long = 180L
    val WRITE_TIMEOUT: Long = 180L
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY


    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor {
        val original = it.request()
        val request = original.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "en")
            .method(original.method, original.body)
            .build()
        it.proceed(request)
    }
    val client = httpClient
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
    val retrofitBuilder = Retrofit.Builder().apply {
        baseUrl(BuildConfig.BASE_URL)
        client(client)
        addConverterFactory(GsonConverterFactory.create())
    }

    return retrofitBuilder.build()
}


fun provideApiServices(retrofit: Retrofit): ImagesApiClient =
    retrofit.create(ImagesApiClient::class.java)

val modules = listOf(ImagesModule,networkModule)