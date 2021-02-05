package com.teamdagger.daggerhiltlearn.di

import com.teamdagger.daggerhiltlearn.repository.MainRepository
import com.teamdagger.daggerhiltlearn.retrofit.BlogRetrofit
import com.teamdagger.daggerhiltlearn.retrofit.NetworkMapper
import com.teamdagger.daggerhiltlearn.room.BlogDao
import com.teamdagger.daggerhiltlearn.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
            blogDao: BlogDao,
            retrofit: BlogRetrofit,
            cacheMapper: CacheMapper,
            networkMapper: NetworkMapper
    ):MainRepository{
        return MainRepository(blogDao,retrofit,cacheMapper,networkMapper)
    }
}