package com.enciyo.data.di

import com.enciyo.data.RepositoryImp
import com.enciyo.data.ResourceDataSource
import com.enciyo.domain.Repository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class DataModule{

    @Provides
    fun provideMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideRepository(
        remoteDataSource: ResourceDataSource
    ) = RepositoryImp(
        remoteDataSource
    ) as Repository

}