package com.enciyo.data.di

import android.content.Context
import androidx.room.Room
import com.enciyo.data.RepositoryImp
import com.enciyo.data.ResourceDataSource
import com.enciyo.data.SatellitesDatabase
import com.enciyo.data.adapter.DateStringAdapter
import com.enciyo.data.local.LocalDataSource
import com.enciyo.domain.Repository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    fun provideMoshi() = Moshi.Builder()
        .add(DateStringAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()


    @Provides
    fun provideRepository(
        resourceDataSource: ResourceDataSource,
        localDataSource: LocalDataSource,
    ): Repository = RepositoryImp(resourceDataSource, localDataSource)


    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SatellitesDatabase::class.java, "DB_NAME") // fix for security
            .fallbackToDestructiveMigration()
            .build()

}