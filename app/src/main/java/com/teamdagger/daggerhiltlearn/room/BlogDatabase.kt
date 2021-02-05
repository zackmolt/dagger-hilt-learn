package com.teamdagger.daggerhiltlearn.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogCacheEntity::class],version =1)
abstract class BlogDatabase : RoomDatabase(){
    abstract fun blogDao():BlogDao

    companion object{
        val DATABSE_NAME = "BLOG_DB"
    }
}