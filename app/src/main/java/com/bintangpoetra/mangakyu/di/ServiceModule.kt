package com.bintangpoetra.mangakyu.di

import com.bintangpoetra.mangakyu.data.manga.remote.MangaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideMangaService(retrofit: Retrofit): MangaService = retrofit.create(MangaService::class.java)

}