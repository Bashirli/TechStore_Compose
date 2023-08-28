package com.bashirli.techstorecompose.di

import android.content.Context
import com.bashirli.techstorecompose.common.util.BASE_URL
import com.bashirli.techstorecompose.data.repo.ApiRepositoryImpl
import com.bashirli.techstorecompose.data.service.Service
import com.bashirli.techstorecompose.data.source.ApiSource
import com.bashirli.techstorecompose.data.source.ApiSourceImpl
import com.bashirli.techstorecompose.domain.repo.ApiRepository
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun injectOkHttp3(@ApplicationContext context: Context)=OkHttpClient
        .Builder()
        .addInterceptor(ChuckerInterceptor(context))
        .build()


    @Singleton
    @Provides
    fun injectRetrofit(okHttpClient: OkHttpClient)=
        Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun injectService(retrofit: Retrofit) = retrofit.create(Service::class.java)

    @Singleton
    @Provides
    fun injectSource(service: Service)=ApiSourceImpl(service) as ApiSource

    @Singleton
    @Provides
    fun injectRepo(apiSource: ApiSource) = ApiRepositoryImpl(apiSource) as ApiRepository


}