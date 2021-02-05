package com.teamdagger.daggerhiltlearn.di

import android.content.Context
import androidx.room.Room
import com.teamdagger.daggerhiltlearn.room.BlogDao
import com.teamdagger.daggerhiltlearn.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context:Context):BlogDatabase{
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABSE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase : BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }

}